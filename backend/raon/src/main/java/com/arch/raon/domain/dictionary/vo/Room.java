package com.arch.raon.domain.dictionary.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketQuizDTO;
import com.arch.raon.global.util.enums.GameState;
import com.arch.raon.global.util.enums.SocketResponse;

/**
 * Room은 국어사전 퀴즈를 플레이하기 위한 사람들이 모인 곳이다.
 *
 * - Room에는 최대 4명이 입장 할 수 있다.
 * - 방에는 한 명의 방장이 있다.
 * - user는 Room에서 나갈 수 있다. 이때 나간 사람이 방장이라면 다음 사람이 방장이 된다.
 * - 방은 "대기", "게임 중"의 상태를 가진다.
 * - "대기" 상태 일 때만 Room에 입장 할 수 있다.
 * - "게임 중"인 상태의 Room에는 입장 할 수 없다.
 *
 */

public class Room {
	private GameState state = GameState.WAITING;
	private final ConcurrentMap<String, User> userInfo = new ConcurrentHashMap<>();
	private List<SocketQuizDTO> quizList = new ArrayList<>();
	private int currentQuizIdx = 0;
	private int submitted = 0;
	private String owner;

	public Room(String nickname){
		userInfo.put(nickname,new User(nickname,0));
		this.owner = nickname;
	}

	//========== WAITING일 때의 메소드들 =========================
	public boolean hasUserNamed(String nickname){
		return userInfo.containsKey(nickname);
	}
	public boolean isRoomFull(int maxPlayer){
		return userInfo.size() >= maxPlayer;
	}
	public int getRoomSize(){return userInfo.size();}

	public List<String> getUsers(){
		List<String> users = new ArrayList<>();
		for(Map.Entry<String, User> entry : userInfo.entrySet()){
			users.add(entry.getKey());
		}
		return users;
	}

	public void enter(String enteredUser){
		userInfo.put(enteredUser,new User(enteredUser, 0));
	}

	// 방에 있는 유저가 방에 나간다.
	public boolean leave(String leaved_nickname){
		if(userInfo.containsKey(leaved_nickname)){
			if(leaved_nickname.equals(owner)){
				changeOwner();
			}
			userInfo.remove(leaved_nickname);
			return true;
		}
		return false;
	}

	// 게임을 시작한다.
	public void PLAY(){
		state = GameState.PLAY;
	}


	//=========== PLAY일 때의 메소드들 ==================================

	/**
	 * 퀴즈를 받아 섞은 후 저장한다.
	 * 퀴즈의 내용과 답을 room에서 가지고 있어야 하므로 List로 퀴즈를 저장한다.
	 */
	public void shuffleAndSetQuizes(DictionaryQuizResDTO pureQuizes) {
		int index = 0;
		int stage = 1;

		while (!pureQuizes.getDirectionQuizList().isEmpty() && !pureQuizes.getInitialQuizList().isEmpty()) {
			boolean isGreater = Math.random() * 100 > 70.0;
			System.out.println("			퀴즈 셔플 isGreater 결과 : " + isGreater);
			SocketQuizDTO nextQuiz = new SocketQuizDTO();

			if (isGreater) {
				index = pureQuizes.getDirectionQuizList().size()-1;
				nextQuiz = new SocketQuizDTO(SocketResponse.DIRECTION_QUIZ
											, stage++
											, pureQuizes.getDirectionQuizList().remove(index)
				);
			}
			else {
				index = pureQuizes.getInitialQuizList().size()-1;
				nextQuiz = new SocketQuizDTO(SocketResponse.INITIAL_QUIZ
											, stage++
											, pureQuizes.getInitialQuizList().remove(index)
				);
			}
			quizList.add(nextQuiz);
		}
		if(pureQuizes.getDirectionQuizList().isEmpty()){
			while(!pureQuizes.getInitialQuizList().isEmpty()){
				quizList.add(new SocketQuizDTO(SocketResponse.INITIAL_QUIZ, stage++, pureQuizes.getInitialQuizList().remove(0)));
			}
		}
		else{
			while(!pureQuizes.getDirectionQuizList().isEmpty()){
				quizList.add(new SocketQuizDTO(SocketResponse.DIRECTION_QUIZ, stage++, pureQuizes.getDirectionQuizList().remove(0)));
			}
		}
	}


	public SocketQuizDTO getNextQuiz(){
		return currentQuizIdx == 10 // 0~9, so 9 is last stage
			 ? null
			 : quizList.get(currentQuizIdx);
	}

	/**
	 * 방 안의 유저가 보낸 답을 비교하고 점수를 업데이트 한다.
	 *
	 * @param nickname
	 * @param stage
	 * @param userAnswer
	 * @param timeSpend
	 */
	public boolean checkAndUpdateScore(String nickname, int stage, String userAnswer, int timeSpend){
		System.out.println("[LOG] nickname:"+nickname+ " userAnswer:"+ userAnswer + " realAnswer:" + quizList.get(currentQuizIdx).getAnswer());

		if(quizList.get(currentQuizIdx).getStage() != stage){
			System.out.println("[비상!!!] 스테이지가 달라!!!"+ " 유저: " + nickname + "stageIDx: "+ currentQuizIdx + " Room의 stage: "+ quizList.get(currentQuizIdx).getStage() +" 유저가 보낸 스테이지: "+ stage );
			return false;
		}
		if(!hasUserNamed(nickname)){
			System.out.println("[비상!!!] 유저가 방에 없어!!!"+ " 유저: " + nickname);
			return false;
		}
		if(timeSpend <= 0){
			System.out.println("[비상!!!] 유저가 0초 미만으로 문제를 풀었어!!!"+ " 유저: " + nickname + " 클리어 시간: " + timeSpend);
			return false;
		}

		if(quizList.get(currentQuizIdx).getAnswer().equals(userAnswer)){
			int point = (100_000 - timeSpend); // TODO: 늦게 풀 수록 점수를 낮게 주고 싶은데 방법이 없나?
			System.out.println(" 			nickname:"+nickname+" answer:" + userAnswer+" point:" + point);
			userInfo.get(nickname).addPoint(point);
		}

		updateUserAnswer(nickname,userAnswer);
		submitted += 1;
		return true;
	}

	// 현재 방에 있는 사람들의 순위를 구한다. 점수가 높은 순서대로 정렬된 리스트 리턴
	public List<User> getRank(){
		List<User> rank = new ArrayList<>();

		for(Map.Entry<String,User> entry : userInfo.entrySet()){
			rank.add(entry.getValue());
		}

		Collections.sort(rank);
		return rank;
	}




	//============ 방장에 대한 메소드들 =========================

	// 방장이 나간 경우 현재 방장을 바꾼다.
	private void changeOwner() {
		// 만약 방에 남은 사람이 존재하는 사람이 존재하는 경우 방장을 바꿔준다.
		if (userInfo.size() != 0) {
			owner = getNextOwner();
		}
	}

	// 아주 못생기게 다음 방장을 구한다.
	private String getNextOwner(){
		String nextOwner = null;
		for(Map.Entry<String,User> entry : userInfo.entrySet()){
			nextOwner = entry.getKey();
			break;
		}
		return nextOwner;
	}


	//=========== 최근 답에 대한 메소드들 ====================
	private boolean updateUserAnswer(String nickname, String newAnswer){
		if(userInfo.containsKey(nickname)){
			userInfo.get(nickname).setLastAnswer(newAnswer);
			return true;
		}
		return false;
	}

	//============= getter and setter ====================

	public String getRoomOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public GameState getState() {
		return state;
	}
	public void setState(GameState state) {
		this.state = state;
	}

	public boolean isAllSubmit() {
		System.out.println("			submitted:"+submitted + " userInfo.size():"+ userInfo.size());
		return submitted == userInfo.size();
	}

	public void clearSubmitted(){
		submitted = 0;
	}

	public void updateQuiz(){
		currentQuizIdx += 1;
	}

	public int getStage() {
		return currentQuizIdx+1;
	}
}

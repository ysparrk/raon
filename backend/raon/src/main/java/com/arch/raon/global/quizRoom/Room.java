package com.arch.raon.global.quizRoom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.SocketResponseDTO;
import com.arch.raon.global.util.enums.GameState;

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
	private final int MAX_PLAYER = 3;
	private GameState state = GameState.WAITING;
	private final ConcurrentMap<String, User> userInfo = new ConcurrentHashMap<>();
	private ConcurrentMap<String, String> latestAnswer = new ConcurrentHashMap<>();
	private DictionaryQuizResDTO quizes;
	private String owner;

	public Room(String nickname){
		userInfo.put(nickname,new User(nickname,0));
		this.owner = nickname;
	}


	//========== WAITING일 때의 메소드들 =========================
	public GameState getCurrentState(){
		return state;
	}

	public boolean isFull(){
		return userInfo.size() >= MAX_PLAYER;
	}

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

	// 현재 방에 있는 사람들의 순위를 구한다. 점수가 높은 순서대로 정렬된 리스트 리턴
	public List<String> getRank(){
		List<String> rank = new ArrayList<>();

		for(Map.Entry<String,User> entry : userInfo.entrySet()){
			rank.add(entry.getKey());
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
	private void addUserToAnswerMap(String nickname){
		latestAnswer.put(nickname,null);
	}
	private boolean addAnswerToMap(String nickname, String newAnswer){
		if(userInfo.containsKey(nickname) && latestAnswer.containsKey(nickname)){
			latestAnswer.replace(nickname, newAnswer);
			return true;
		}
		return false;
	}

	//============= getter and setter ====================

	public String getOwner() {
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
	public void setQuizes(DictionaryQuizResDTO quizes) {
		this.quizes = quizes;
	}
}

package com.arch.raon.domain.dictionary.vo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketJoinResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketQuizDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketStageResultResDTO;
import com.arch.raon.global.util.enums.GameState;
import com.arch.raon.global.util.enums.SocketResponse;

/**
 * Room들을 모아 둔 자료구조이다. static으로 설정하여 전역에서 참조 가능하다.
 *
 * - 여러 방을 가진다.
 * - 방의 삭제 추가를 관리한다.
 */
public class Rooms {
	private static final int MAX_PLAYERS = 3;
	private static final ConcurrentMap<String, Room> rooms = new ConcurrentHashMap<>();

	/**
	 * 해당 id를 가진 방이 존재하는지 확인
	 *
	 * @param roomId : 방 id
	 * @return true  : 해당 id를 가진 방 존재
	 *         false : 해당 id를 가진 방 존재 안함
	 */
	public static boolean hasRoomThatIdIs(String roomId){
		return rooms.containsKey(roomId);
	}

	/**
	 * roomId를 가진 Room객체 리턴
	 * !주의! 반드시 먼저 roomId가 valid한지 체크 하고 사용 할 것.
	 *
	 * @param roomId
	 * @return Room that has roomId
	 */
	private static Room roomOf(String roomId){
		return rooms.get(roomId);
	}

	/**
	 * 방을 생성한다.
	 *
	 * @param roomId
	 * @param ownerNickname
	 * @return true  : 방 생성 성공
	 *         false : 방 생성 실패
	 */
	public static boolean makeRoom(String roomId, String ownerNickname){
		if(hasRoomThatIdIs(roomId)){
			return false;
		}
		else {
			rooms.put(roomId, new Room(ownerNickname));
			return true;
		}
	}

	public static SocketQuizDTO getNextQuizFrom(String roomId){
		return roomOf(roomId).getNextQuiz();
	}


	/**
	 * 유저가 방을 나간다.
	 *
	 * @param roomId
	 * @param leaved
	 * @return true  : roomId를 가진 방이 존재하고, 그 방에 유저가 있을 때 방을 나감
	 * 		   false : roomId를 가진 방이 존재하지 않거나 방이 존재하더라도 방에 그 유저가 없는 경우
	 */
	public static boolean leaveRoom(String roomId, String leaved){
		if(hasRoomThatIdIs(roomId) && roomOf(roomId).hasUserNamed(leaved)){
			rooms.get(roomId).leave(leaved);

			if(rooms.get(roomId).getRoomSize() == 0){
				rooms.remove(roomId);
			}
			return true;
		}
		return false;
	}

	/**
	 * 방장이 게임을 시작한다.
	 *
	 * 두 가지 방법이 있다.
	 * 1. 퀴즈셋(문제, 정답)을 전부 프론트로 보낸다.
	 * 2. 퀴즈셋을 백에서만 가지고 있는다.
	 * 		-> 11/06 기준 이게 맞는 것 같다. 왜냐?
	 * 		1번 기준 이 메소드에서 퀴즈 데이터를 받아서 보내야 하는데 여기는 boolean이다.
	 *
	 * @param roomId
	 * @param nickname
	 * @return
	 */
	public static boolean gameStart(String roomId, String nickname){
		if(hasRoomThatIdIs(roomId) && roomOf(roomId).getRoomOwner().equals(nickname)){
			roomOf(roomId).PLAY();
			return true;
		}
		return false;
	}

	public static SocketJoinResDTO getUsersOf(String roomId){
		return new SocketJoinResDTO(roomOf(roomId).getRoomOwner(), roomOf(roomId).getUsers());
	}

	/**
	 * 랜덤으로 생성한 퀴즈를 Room에 저장한다.
	 *
	 * 일단 각 방에서 퀴즈를 가지고 있어야 하므로 setter를 이용하여 저장한다.
	 * 퀴즈를 넣을 때 알아서 순서를 만들어서 넣어야 한다.
	 *
	 *
	 * @param roomId
	 * @param dictionaryQuizResDTO
	 * @return
	 */
	public static boolean addQuizesToRoom(String roomId, DictionaryQuizResDTO dictionaryQuizResDTO){
		if(hasRoomThatIdIs(roomId) && roomOf(roomId).getState() == GameState.PLAY){
			roomOf(roomId).shuffleAndSetQuizes(dictionaryQuizResDTO);
		}
		return false;
	}

	/**
	 * 해당 방에 유저가 입장한다.
	 *
	 * 반드시 roomId가 유효한지 체크하고 사용해야 한다.
	 *
	 * @param nickname
	 * @param roomId
	 */
	public static void userEnterToRoom(String nickname, String roomId) {
		roomOf(roomId).enter(nickname);
	}

	public static boolean isUserInRoom(String nickname, String roomId){
		return roomOf(roomId).hasUserNamed(nickname);
	}

	public static boolean isRoomPlaying(String roomId){
		return GameState.PLAY == roomOf(roomId).getState();
	}

	public static boolean isUserOwner(String roomId, String nickname){
		return roomOf(roomId).getRoomOwner().equals(nickname);
	}

	public static String getOwnerOf(String roomId){
		return roomOf(roomId).getRoomOwner();
	}

	public static boolean isThatRoomFull(String roomId){
		return roomOf(roomId).isRoomFull(MAX_PLAYERS);
	}

	public static void addUserAnswer(String roomId, String nickname, String userAnswer,int stage, int timeSpend) {
		roomOf(roomId).checkAndUpdateScore(nickname,stage,userAnswer,timeSpend);
	}

	public static boolean isAllSubmit(String roomId){
		return roomOf(roomId).isAllSubmit();
	}

	public static SocketStageResultResDTO getStageResult(String roomId) {
		return new SocketStageResultResDTO(roomOf(roomId).getRank(), SocketResponse.STAGE_RESULT);
	}

	public static boolean isLastStage(String roomId) {
		return roomOf(roomId).getStage() == 9;
	}

	public static void updateNextQuiz(String roomId) {
		roomOf(roomId).clearSubmitted();
		roomOf(roomId).updateQuiz();
	}
}

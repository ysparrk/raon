package com.arch.raon.global.quizRoom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.arch.raon.global.util.enums.GameState;

/**
 * Room은 국어사전 퀴즈를 플레이하기 위한 사람들이 모인 곳이다.
 *
 * 1. Room에는 최대 4명이 입장 할 수 있다.
 * 2. user는 Room에서 나갈 수 있다. 이때 나간 사람이 방장이라면 다음 사람이 방장이 된다.
 * 3. 방은 "대기", "게임 중"의 상태를 가진다.
 * 4. "대기" 상태 일 때만 Room에 입장 할 수 있다.
 * 5. "게임 중"인 상태의 Room에는 입장 할 수 없다.
 */
public class Room {
	private final int MAX_PLAYER = 4;

	// WAITING, PLAY
	private GameState state = GameState.WAITING;

	// Key : nickname, value : MemberInfo
	private ConcurrentMap<String, User> roomInfo = new ConcurrentHashMap<>();

	// Key : nickname, value : answer, 즉 최근의 문제에 대한 답만 가지고 있는다.
	private ConcurrentMap<String, String> latestAnswer = new ConcurrentHashMap<>();

	public Room(String nickname){
		roomInfo.put(nickname,new User(nickname,0, true));
	}


	//========== WAITING일 때의 메소드들 =========================

	// 새로운 유저가 방에 참여한다.
	public boolean addUser(String joined_nickname){
		if(roomInfo.size() < MAX_PLAYER && state.equals(GameState.WAITING)){
			// TODO: test needed
			synchronized(roomInfo){
				roomInfo.put(joined_nickname, new User(joined_nickname,0));
			}
			return true;
		}
		return false;
	}

	// 방에 있는 유저가 방에 나간다.
	public boolean leaveUser(String leaved_nickname){
		if(roomInfo.containsKey(leaved_nickname)){
			boolean isOwner = roomInfo.get(leaved_nickname).isOwner();
			if(isOwner){
				changeOwner();
			}

			roomInfo.remove(leaved_nickname);
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

		for(Map.Entry<String,User> entry : roomInfo.entrySet()){
			rank.add(entry.getKey());
		}

		Collections.sort(rank);
		return rank;
	}


	//============ 방장에 대한 메소드들 =========================

	// 방장이 나간 경우 현재 방장을 바꾼다.
	private void changeOwner() {
		// 만약 방에 아무도 없을 경우 방장의 변경은 이뤄지지 않는다.
		if (roomInfo.size() != 0) {
			String nextOwner = getNextOwner();
			roomInfo.get(nextOwner).setOwner(true);
		}
	}

	// 아주 못생기게 다음 방장을 구한다.
	private String getNextOwner(){
		String next = null;
		for(Map.Entry<String,User> entry : roomInfo.entrySet()){
			next = entry.getKey();
			break;
		}
		return next;
	}


	//=========== 최근 답에 대한 메소드들 ====================
	private void addUserToAnswerMap(String nickname){
		latestAnswer.put(nickname,null);
	}

}

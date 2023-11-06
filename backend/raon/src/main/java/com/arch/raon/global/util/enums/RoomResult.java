package com.arch.raon.global.util.enums;

public enum RoomResult {

	FAIL_INVALID_USER("유효하지 않는 유저"),
	FAIL_NONEXIST_ROOM("존재하지 않는 방에 대한 요청"),


	CREATE_SUCCESS("방 생성 성공"),
	CREATE_FAIL_SAME_ROOMID("동일한 키에 대한 방 생성 요청"),

	JOIN_SUCCESS("방 입장 성공"),
	JOIN_FAIL_FULL("만원인 방 입장 시도"),
	JOIN_FAIL_PLAYING("이미 게임 중인 방에 입장 시도"),

	LEAVE_SUCCESS("방 나가기 성공"),
	LEAVE_FAIL_NONEXIST("존재하지 않는 방에 대한 나가기 요청"),

	GAME_START_SUCCESS("게임 시작 성공"),
	GAME_START_FAIL_NOT_A_OWNER("방장이 아닌데 게임 시작 요청"),
	GAME_STAGE_DATA_SEND_COMPLETE("각 스테이지 데이터 수신 성공"),
	GAME_STAGE_DATA_SEND_FAIL("각 스테이지 데이터 수신 실패");

	private String description;
	RoomResult(String description) {
		this.description = description;
	}
}

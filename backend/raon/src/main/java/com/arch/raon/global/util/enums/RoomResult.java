package com.arch.raon.global.util.enums;

public enum RoomResult {

	FAIL_INVALID_USER("유효하지 않는 유저"),

	CREATE_SUCCESS("방 생성 성공"),
	CREATE_FAIL_SAME_ROOMID("동일한 키에 대한 방 생성 요청"),

	JOIN_SUCCESS("방 입장 성공"),
	JOIN_FAIL_NONEXIST("존재하지 않는 방 입장 시도"),
	JOIN_FAIL_FULL("만원인 방 입장 시도"),
	JOIN_FAIL_PLAYING("이미 게임 중인 방에 입장 시도"),


	LEAVE_SUCCESS("방 나가기 성공"),
	LEAVE_FAIL_NONEXIST("존재하지 않는 방에 대한 나가기 요청");


	private String description;
	RoomResult(String description) {
		this.description = description;
	}
}

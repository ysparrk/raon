package com.arch.raon.global.util.enums;

public enum SocketResponse {
	
	
	ENTER("방 생성/참가"),
	LEAVE("방 참가"),
	GAME_READY("게임 시작"),
	STAGE_START("스테이지 시작"),
	STAGE_RESULT("스테이지 결과"),
	FINAL_RESULT("최종 결과"),

	DIRECTION_QUIZ("동서남북 퀴즈"),
	INITIAL_QUIZ("초성 퀴즈")

	;


	private String description;

	SocketResponse(String description) {
		this.description = description;
	}
}

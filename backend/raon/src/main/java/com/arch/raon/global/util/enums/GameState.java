package com.arch.raon.global.util.enums;

public enum GameState {
	WAITING("대기"),
	PLAY("게임 중");

	private String state;
	GameState(String state) {
		this.state = state;
	}
}

package com.arch.raon.global.util.enums;

public enum QuizType {
	DIRECTION_QUIZ("동서남북 퀴즈"),
	INITIAL_QUIZ("초성 퀴즈");

	private String message;
	QuizType(String message) {
		this.message = message;
	}
}

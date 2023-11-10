package com.arch.raon.domain.dictionary.dto.request;

import java.io.Serializable;

public class SocketQuizReqDTO implements Serializable {
	private String nickname;

	private String roomId;

	private String userAnswer;

	public SocketQuizReqDTO() {
		super();
	}

	public SocketQuizReqDTO(String nickname, String roomId, String userAnswer) {
		this.nickname = nickname;
		this.roomId = roomId;
		this.userAnswer = userAnswer;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
}

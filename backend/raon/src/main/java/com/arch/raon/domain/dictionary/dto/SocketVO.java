package com.arch.raon.domain.dictionary.dto;

import java.io.Serializable;

public class SocketVO implements Serializable {
	private String nickname;
	private String roomId;

	private String message;

	public SocketVO(String nickname, String roomId) {
		this.nickname = nickname;
		this.roomId = roomId;
	}



	public SocketVO() {
		super();
	}

	public String getNickname() {
		return nickname;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

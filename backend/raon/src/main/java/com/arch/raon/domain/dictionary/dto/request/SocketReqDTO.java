package com.arch.raon.domain.dictionary.dto.request;

import java.io.Serializable;

public class SocketReqDTO implements Serializable {
	private String nickname;
	private String roomId;

	public SocketReqDTO() {
		super();
	}

	public SocketReqDTO(String nickname, String roomId) {
		this.nickname = nickname;
		this.roomId = roomId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Override
	public String toString() {
		return "SocketReqDTO{" +
			"nickname='" + nickname + '\'' +
			", roomId='" + roomId + '\'' +
			'}';
	}
}

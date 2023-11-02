package com.arch.raon.domain.dictionary.dto.request;

public class SocketReqDTO {
	private String nickname;
	private String roomId;

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
}

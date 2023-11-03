package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;

public class SocketResponseDTO implements Serializable {
	private String nickname;
	private String roomId;

	private boolean isOwner;

	private String message;

	public SocketResponseDTO(String nickname, String roomId) {
		this.nickname = nickname;
		this.roomId = roomId;
	}

	public SocketResponseDTO(String nickname, boolean isOwner) {
		this.nickname = nickname;
		this.isOwner = isOwner;
	}



	public SocketResponseDTO() {
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

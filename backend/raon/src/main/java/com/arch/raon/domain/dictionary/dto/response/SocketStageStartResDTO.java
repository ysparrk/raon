package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;

import com.arch.raon.global.util.enums.SocketResponse;

public class SocketStageStartResDTO implements Serializable {
	private SocketResponse message;

	public SocketStageStartResDTO() {
		super();
	}
	public SocketStageStartResDTO(SocketResponse message) {
		this.message = message;
	}

	public SocketResponse getMessage() {
		return message;
	}

	public void setMessage(SocketResponse message) {
		this.message = message;
	}
}

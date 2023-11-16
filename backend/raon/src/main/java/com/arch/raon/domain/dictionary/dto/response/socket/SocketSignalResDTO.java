package com.arch.raon.domain.dictionary.dto.response.socket;

import java.io.Serializable;

import com.arch.raon.global.util.enums.SocketResponse;

public class SocketSignalResDTO implements Serializable {
	private SocketResponse message;

	public SocketSignalResDTO() {
		super();
	}
	public SocketSignalResDTO(SocketResponse message) {
		this.message = message;
	}

	public SocketResponse getMessage() {
		return message;
	}

	public void setMessage(SocketResponse message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SocketSignalResDTO{" +
			"message=" + message +
			'}';
	}
}

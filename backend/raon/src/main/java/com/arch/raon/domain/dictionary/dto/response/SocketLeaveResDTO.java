package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;

public class SocketLeaveResDTO implements Serializable {
	private String leaved;
	private String nextOwner = "none";

	private String message;

	public SocketLeaveResDTO() {
	}


	public SocketLeaveResDTO(String leaved, String nextOwner, String message) {
		this.leaved = leaved;
		this.nextOwner = nextOwner;
		this.message = message;
	}
}

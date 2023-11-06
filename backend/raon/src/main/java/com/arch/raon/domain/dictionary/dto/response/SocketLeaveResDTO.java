package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;

public class SocketLeaveResDTO implements Serializable {
	private String leaved;
	private String nextOwner;

	public SocketLeaveResDTO() {
	}

	public SocketLeaveResDTO(String leaved, String nextOwner) {
		this.leaved = leaved;
		this.nextOwner = nextOwner;
	}
}

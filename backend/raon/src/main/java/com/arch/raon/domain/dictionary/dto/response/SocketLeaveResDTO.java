package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;

public class SocketLeaveResDTO implements Serializable {
	private String leaved;
	private String nextOwner = "none";

	private String message;

	public SocketLeaveResDTO() {
		super();
	}


	public SocketLeaveResDTO(String leaved, String nextOwner, String message) {
		this.leaved = leaved;
		this.nextOwner = nextOwner;
		this.message = message;
	}

	@Override
	public String toString() {
		return "SocketLeaveResDTO{" +
			"leaved='" + leaved + '\'' +
			", nextOwner='" + nextOwner + '\'' +
			", message='" + message + '\'' +
			'}';
	}

	public String getLeaved() {
		return leaved;
	}

	public void setLeaved(String leaved) {
		this.leaved = leaved;
	}

	public String getNextOwner() {
		return nextOwner;
	}

	public void setNextOwner(String nextOwner) {
		this.nextOwner = nextOwner;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

package com.arch.raon.domain.dictionary.dto.response.socket;

import java.io.Serializable;
import java.util.List;

import com.arch.raon.domain.dictionary.vo.User;
import com.arch.raon.global.util.enums.SocketResponse;

public class SocketLeaveResDTO implements Serializable {
	private String leaved;
	private String nextOwner = "none";

	private List<User> lefts;

	private SocketResponse message;

	public SocketLeaveResDTO() {
		super();
	}

	public SocketLeaveResDTO(String leaved, String nextOwner, List<User> lefts, SocketResponse message) {
		this.leaved = leaved;
		this.nextOwner = nextOwner;
		this.lefts = lefts; // 방에 남은 사람들
		this.message = message;
	}

	@Override
	public String toString() {
		return "SocketLeaveResDTO{" +
			"leaved='" + leaved + '\'' +
			", nextOwner='" + nextOwner + '\'' +
			", lefts=" + lefts +
			", message=" + message +
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

	public SocketResponse getMessage() {
		return message;
	}

	public void setMessage(SocketResponse message) {
		this.message = message;
	}

	public List<User> getLefts() {
		return lefts;
	}

	public void setLefts(List<User> lefts) {
		this.lefts = lefts;
	}
}

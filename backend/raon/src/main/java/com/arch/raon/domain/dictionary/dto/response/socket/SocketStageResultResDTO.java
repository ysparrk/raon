package com.arch.raon.domain.dictionary.dto.response.socket;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.arch.raon.domain.dictionary.vo.User;
import com.arch.raon.global.util.enums.SocketResponse;

public class SocketStageResultResDTO implements Serializable {
	private int stage;
	private List<User> users;
	private SocketResponse message;

	public SocketStageResultResDTO() {
		super();
	}
	public SocketStageResultResDTO(int stage, List<User> users, SocketResponse message) {
		this.stage = stage;
		this.users = users;
		this.message = message;
		Collections.sort(this.users);
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public SocketResponse getMessage() {
		return message;
	}

	public void setMessage(SocketResponse message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder usersToString = new StringBuilder();
		for(User user:users){
			usersToString.append(user.toString()).append("\n");
		}

		return "SocketStageResultResDTO{" +
			"stage=" + stage +
			", users=" + usersToString +
			", message=" + message +
			'}';
	}
}

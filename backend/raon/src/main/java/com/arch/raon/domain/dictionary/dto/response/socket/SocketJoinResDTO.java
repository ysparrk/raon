package com.arch.raon.domain.dictionary.dto.response.socket;

import java.io.Serializable;
import java.util.List;

import com.arch.raon.domain.dictionary.vo.User;
import com.arch.raon.global.util.enums.SocketResponse;

/**
 * 방에 입장 성공했을 때 방에 있는 사람들의 정보를 저장한 DTO
 * (방금 입장 한 사람 포함)
 *
 */
public class SocketJoinResDTO implements Serializable {

	private SocketResponse message;
	private String newComer; // 방금 들어온 녀석, 새로운 사람 입장 시 알림 용도로 넣음
	private String owner; // 현재 방장
	private List<User> users; // 방에 있는 사람들(방금 들어온 녀석 포함)

	public SocketJoinResDTO(){
		super();
	}

	public SocketJoinResDTO(String owner, List<User> users) {
		this.owner = owner;
		this.users = users;
	}

	public SocketJoinResDTO(String newComer, String owner, List<User> users) {
		this.newComer = newComer;
		this.owner = owner;
		this.users = users;
	}


	@Override
	public String toString() {
		StringBuilder users = new StringBuilder();
		for(User user: this.users){
			users.append(user).append(" ");
		}



		return "SocketJoinResDTO{" +
			", newComer='" + newComer + '\'' +
			", users=" + users +
			'}';
	}

	public String getNewComer() {
		return newComer;
	}

	public void setNewComer(String newComer) {
		this.newComer = newComer;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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
}

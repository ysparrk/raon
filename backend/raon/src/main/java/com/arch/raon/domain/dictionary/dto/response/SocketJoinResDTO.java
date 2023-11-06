package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * 방에 입장 성공했을 때 방에 있는 사람들의 정보를 저장한 DTO
 * (방금 입장 한 사람 포함)
 *
 */
public class SocketJoinResDTO implements Serializable {
	private String roomId; // 사실 없어도 됨
	private String newComer; // 방금 들어온 녀석, 새로운 사람 입장 시 알림 용도로 넣음
	private String owner; // 현재 방장
	private List<String> users; // 방에 있는 사람들(방금 들어온 녀석 포함)

	public SocketJoinResDTO(){
		super();
	}

	public SocketJoinResDTO(String newComer, String owner, List<String> users) {
		this.newComer = newComer;
		this.owner = owner;
		this.users = users;
	}


	@Override
	public String toString() {
		StringBuilder users = new StringBuilder();
		for(String user: this.users){
			users.append(user).append(" ");
		}


		return "SocketJoinResDTO{" +
			"roomId='" + roomId + '\'' +
			", newComer='" + newComer + '\'' +
			", users=" + users +
			'}';
	}
}

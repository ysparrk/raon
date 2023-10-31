package com.arch.raon.global.quizRoom;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
	private String nickname; // 유저 닉네임

	private int current_point; // 현재 점수

	private boolean isOwner; // 방장 여부

	public User() {
		super();
	}

	public User(String nickname, int current_point) {
		this.nickname = nickname;
		this.current_point = current_point;
	}

	public User(String nickname, int current_point, boolean isOwner) {
		this.nickname = nickname;
		this.current_point = current_point;
		this.isOwner = isOwner;
	}

	public String getNickname() {
		return nickname;
	}

	public int getCurrent_point() {
		return current_point;
	}



	public boolean isOwner() {
		return isOwner;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setCurrent_point(int current_point) {
		this.current_point = current_point;
	}

	public void setOwner(boolean owner) {
		isOwner = owner;
	}

	public void addPoint(int point){
		point = Math.max(point, 0);

		this.current_point += point;
	}



	// 유저의 점수가 큰 순서대로 정렬하기 위해
	@Override
	public int compareTo(User o) {
		return Integer.compare(o.current_point, this.current_point);
	}
}

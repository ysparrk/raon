package com.arch.raon.domain.dictionary.vo;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
	private String nickname; // 유저 닉네임, 닉네임은 중복 불가로 결정했으므로 이거로 구별
	private int current_point; // 현재 점수


	public User() {
		super();
	}

	public User(String nickname, int current_point) {
		this.nickname = nickname;
		this.current_point = current_point;
	}

	public String getNickname() {
		return nickname;
	}

	public int getCurrent_point() {
		return current_point;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setCurrent_point(int current_point) {
		this.current_point = current_point;
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

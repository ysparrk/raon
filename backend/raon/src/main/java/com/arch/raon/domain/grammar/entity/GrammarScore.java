package com.arch.raon.domain.grammar.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GrammerScore")
public class GrammarScore {

	@Id
	private Long id;
	private Long member_id;
	private int score;
	private int play_time;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;

	public GrammarScore() {
	}

	public GrammarScore(Long id, Long member_id, int score, int play_time, LocalDateTime created_at,
		LocalDateTime modified_at) {
		setId(id);
		setMember_id(member_id);
		setScore(score);
		setPlay_time(play_time);
		setCreated_at(created_at);
		setModified_at(modified_at);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPlay_time() {
		return play_time;
	}

	public void setPlay_time(int play_time) {
		this.play_time = play_time;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getModified_at() {
		return modified_at;
	}

	public void setModified_at(LocalDateTime modified_at) {
		this.modified_at = modified_at;
	}
}

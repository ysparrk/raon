package com.arch.raon.domain.grammar.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "grammar_score")
public class GrammarScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	@Column(name = "member_id", nullable = false)
	private Long member_id;
	@Column(name = "score", nullable = false)
	private int score;
	@Column(name = "play_time", nullable = false)
	private int play_time;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime created_at;
	@UpdateTimestamp
	@Column(name = "modified_at", nullable = false)
	private LocalDateTime modified_at;

	public GrammarScore() {
		super();
	}

	@Builder
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

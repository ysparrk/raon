package com.arch.raon.domain.grammar.entity;

import java.time.LocalDateTime;

import lombok.Getter;
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
@Getter
public class GrammarScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	@Column(name = "member_id", nullable = false)
	private Long member_id;
	@Column(name = "score", nullable = false)
	private int score;

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
	public GrammarScore(Long id, Long member_id, int score, LocalDateTime created_at, LocalDateTime modified_at) {
		this.id = id;
		this.member_id = member_id;
		this.score = score;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}
}

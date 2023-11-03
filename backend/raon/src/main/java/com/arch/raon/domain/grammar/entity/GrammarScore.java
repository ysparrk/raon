package com.arch.raon.domain.grammar.entity;

import java.time.LocalDateTime;

import com.arch.raon.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;

@Entity
@Table(name = "grammar_score")
@Getter
public class GrammarScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@Column(name = "score", nullable = false)
	private int score;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "modified_at", nullable = false)
	private LocalDateTime modifiedAt;

	public GrammarScore() {
		super();
	}

	@Builder
	public GrammarScore(Long id, Member member, int score, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		this.id = id;
		this.member = member;
		this.score = score;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
}

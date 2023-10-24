package com.arch.raon.domain.grammer.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Grammer_Quiz")
public class GrammerQuiz implements Serializable {
	@Id
	private Long id;
	private String content;
	private String option_one;
	private String option_two;
	private String answer;
	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(name = "modified_at", nullable = false)
	private LocalDateTime modifiedAt;


	public GrammerQuiz() {
		super();
	}

	public GrammerQuiz(Long id, String content, String option_one, String option_two, String answer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		this.id = id;
		this.content = content;
		this.option_one = option_one;
		this.option_two = option_two;
		this.answer = answer;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOption_one() {
		return option_one;
	}

	public void setOption_one(String option_one) {
		this.option_one = option_one;
	}

	public String getOption_two() {
		return option_two;
	}

	public void setOption_two(String option_two) {
		this.option_two = option_two;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}

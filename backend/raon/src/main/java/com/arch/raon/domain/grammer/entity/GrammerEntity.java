package com.arch.raon.domain.grammer.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GrammerEntity implements Serializable {
	@Id
	private Long id;
	private String content;
	private String option_one;
	private String option_two;
	private String answer;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;


	public GrammerEntity() {
		super();
	}

	public GrammerEntity(Long id, String content, String option_one, String option_two, String answer,
		LocalDateTime created_at, LocalDateTime modified_at) {

		setId(id);
		setContent(content);
		setOption_one(option_one);
		setOption_two(option_two);
		setAnswer(answer);
		setCreated_at(created_at);
		setModified_at(modified_at);
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

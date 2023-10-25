package com.arch.raon.domain.grammar.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grammer_quiz")
public class GrammarQuiz implements Serializable {
	@Id
	private Long id;
	private String content;
	private String option_one;
	private String option_two;
	private String answer;


	public GrammarQuiz() {
		super();
	}

	public GrammarQuiz(Long id, String content, String option_one, String option_two, String answer) {
		this.id = id;
		this.content = content;
		this.option_one = option_one;
		this.option_two = option_two;
		this.answer = answer;

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
}

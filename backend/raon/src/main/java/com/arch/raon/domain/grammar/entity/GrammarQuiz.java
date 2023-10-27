package com.arch.raon.domain.grammar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grammar_quiz")
public class GrammarQuiz implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "content", length = 255, nullable = false)
	private String content;

	@Column(name = "option_one", length = 32, nullable = false)
	private String option_one;
	@Column(name = "option_two", length = 32, nullable = false)
	private String option_two;
	@Column(name = "answer", length = 32, nullable = false)
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

	@Override
	public String toString() {
		return "GrammarQuiz{" +
			"id=" + id +
			", content='" + content + '\'' +
			", option_one='" + option_one + '\'' +
			", option_two='" + option_two + '\'' +
			", answer='" + answer + '\'' +
			'}';
	}
}

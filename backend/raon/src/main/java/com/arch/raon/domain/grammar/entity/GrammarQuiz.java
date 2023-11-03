package com.arch.raon.domain.grammar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "grammar_quiz")
@Getter
public class GrammarQuiz implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "content", length = 255, nullable = false)
	private String content;

	@Column(name = "option_one", length = 32, nullable = false)
	private String option_one;
	@Column(name = "option_two", length = 32, nullable = false)
	private String option_two;
	@Column(name = "answer", length = 32, nullable = false)
	private String answer;

	@Column(name = "submit")
	private Integer submit;

	@Column(name = "hit")
	private Integer hit;


	public GrammarQuiz() {
		super();
	}

	public GrammarQuiz(Long id, String content, String option_one, String option_two, String answer, Integer submit, Integer hit) {
		this.id = id;
		this.content = content;
		this.option_one = option_one;
		this.option_two = option_two;
		this.answer = answer;
		this.submit = submit;
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "GrammarQuiz{" +
				"id=" + id +
				", content='" + content + '\'' +
				", option_one='" + option_one + '\'' +
				", option_two='" + option_two + '\'' +
				", answer='" + answer + '\'' +
				", submit=" + submit +
				", hit=" + hit +
				'}';
	}

	public void quizCorrect() {
		this.submit += 1;
		this.hit += 1;
	}

	public void quizNotCorrect(){
		this.submit += 1;
	}

}

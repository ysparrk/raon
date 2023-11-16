package com.arch.raon.domain.grammar.dto.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GrammarQuizResDTO implements Serializable {

	private Long id;
	private String content;
	private String option_one;
	private String option_two;
	private String answer;
	private Integer answer_percent;

	public GrammarQuizResDTO(){
		super();
	}

	@Builder
	public GrammarQuizResDTO(Long id, String content, String option_one, String option_two, String answer, Integer answer_percent) {
		this.id = id;
		this.content = content;
		this.option_one = option_one;
		this.option_two = option_two;
		this.answer = answer;
		this.answer_percent = answer_percent;
	}

	@Override
	public String toString() {
		return "GrammarQuizResDTO{" +
				"id=" + id +
				", content='" + content + '\'' +
				", option_one='" + option_one + '\'' +
				", option_two='" + option_two + '\'' +
				", answer='" + answer + '\'' +
				", answer_percent=" + answer_percent +
				'}';
	}
}

package com.arch.raon.domain.grammar.dto.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GrammarQuizResDto implements Serializable {

	private Long id;
	private String content;
	private String option_one;
	private String option_two;
	private String answer;

	public GrammarQuizResDto(){
		super();
	}

	@Builder
	public GrammarQuizResDto(Long id, String content, String option_one, String option_two, String answer) {
		this.id = id;
		this.content = content;
		this.option_one = option_one;
		this.option_two = option_two;
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "GrammarQuizResDto{" +
			"id=" + id +
			", content='" + content + '\'' +
			", option_one='" + option_one + '\'' +
			", option_two='" + option_two + '\'' +
			", answer='" + answer + '\'' +
			'}';
	}
}

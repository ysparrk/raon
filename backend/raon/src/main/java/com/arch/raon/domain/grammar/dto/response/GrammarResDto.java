package com.arch.raon.domain.grammar.dto.response;

import java.io.Serializable;

public class GrammarResDto implements Serializable {
	private String content;
	private String option_one;
	private String option_two;
	private String answer;

	public GrammarResDto(){
		super();
	}

	public GrammarResDto(String content, String option_one, String option_two, String answer) {
		setContent(content);
		setAnswer(answer);
		setOption_one(option_one);
		setOption_two(option_two);
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
}

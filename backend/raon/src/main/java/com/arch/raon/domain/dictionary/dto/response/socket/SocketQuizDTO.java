package com.arch.raon.domain.dictionary.dto.response.socket;

import java.io.Serializable;

import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SocketQuizDTO implements Serializable {
	private String quizType;

	/**
	 * 퀴즈는 초성 퀴즈 or 동서남북 퀴즈 중 하나 이므로
	 * 두 필드 중 하나는 null이다.
	 * 이때 jackon으로 serialize 할 때 null인 데이터를 serialize하면
	 * 에러가 발생하므로 null일때 serialize의 대상에서 제외하는
	 * JsonInclude 어노테이션을 붙인다.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private DictionaryInitialQuiz dictionaryInitialQuiz;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private DictionaryDirectionQuiz dictionaryDirectionQuiz;

	public SocketQuizDTO() {
		super();
	}

	public SocketQuizDTO(String quizType, DictionaryInitialQuiz dictionaryInitialQuiz) {
		this.quizType = quizType;
		this.dictionaryInitialQuiz = dictionaryInitialQuiz;
	}

	public SocketQuizDTO(String quizType, DictionaryDirectionQuiz dictionaryDirectionQuiz) {
		this.quizType = quizType;
		this.dictionaryDirectionQuiz = dictionaryDirectionQuiz;
	}

	public String getQuizType() {
		return quizType;
	}

	public void setQuizType(String quizType) {
		this.quizType = quizType;
	}

	public DictionaryInitialQuiz getDictionaryInitialQuiz() {
		return dictionaryInitialQuiz;
	}

	public void setDictionaryInitialQuiz(DictionaryInitialQuiz dictionaryInitialQuiz) {
		this.dictionaryInitialQuiz = dictionaryInitialQuiz;
	}

	public DictionaryDirectionQuiz getDictionaryDirectionQuiz() {
		return dictionaryDirectionQuiz;
	}

	public void setDictionaryDirectionQuiz(
		DictionaryDirectionQuiz dictionaryDirectionQuiz) {
		this.dictionaryDirectionQuiz = dictionaryDirectionQuiz;
	}

	@Override
	public String toString() {
		return "SocketQuizDTO{" +
			"quizType='" + quizType + '\'' +
			", dictionaryInitialQuiz=" + dictionaryInitialQuiz +
			", dictionaryDirectionQuiz=" + dictionaryDirectionQuiz +
			'}';
	}
}

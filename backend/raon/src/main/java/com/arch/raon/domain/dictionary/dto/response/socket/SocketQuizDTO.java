package com.arch.raon.domain.dictionary.dto.response.socket;

import java.io.Serializable;

import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.global.util.enums.QuizType;
import com.arch.raon.global.util.enums.SocketResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SocketQuizDTO implements Serializable {
	private SocketResponse message;
	private int stage;

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

	public SocketQuizDTO(SocketResponse message, int stage, DictionaryInitialQuiz dictionaryInitialQuiz) {
		this.message = message;
		this.stage = stage;
		this.dictionaryInitialQuiz = dictionaryInitialQuiz;
	}

	public SocketQuizDTO(SocketResponse message, int stage, DictionaryDirectionQuiz dictionaryDirectionQuiz) {
		this.message = message;
		this.stage = stage;
		this.dictionaryDirectionQuiz = dictionaryDirectionQuiz;
	}

	public SocketResponse getMessage() {
		return message;
	}

	public void setMessage(SocketResponse message) {
		this.message = message;
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

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public String getAnswer(){
		return dictionaryDirectionQuiz == null
			 ? dictionaryInitialQuiz.getWord()
			 : dictionaryDirectionQuiz.getAnswer();
	}

	@Override
	public String toString() {
		return "SocketQuizDTO{" +
			"message=" + message +
			", stage=" + stage +
			", dictionaryInitialQuiz=" + dictionaryInitialQuiz +
			", dictionaryDirectionQuiz=" + dictionaryDirectionQuiz +
			'}';
	}
}

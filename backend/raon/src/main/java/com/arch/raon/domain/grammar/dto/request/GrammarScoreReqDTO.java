package com.arch.raon.domain.grammar.dto.request;

import java.io.Serializable;

import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GrammarScoreReqDTO implements Serializable {
	private int score;		// 최대 100
	private int play_time; // 단위 : ms

	public GrammarScoreReqDTO() {
		super();
	}

	@Builder
	public GrammarScoreReqDTO(int score, int play_time) {
		setScore(score);
		setPlay_time(play_time);
	}

	public void setScore(int score) {
		if(score <=0 || score > 100){
			throw new CustomException(ErrorCode.GRAMMAR_SCORE_UNAVAILABLE) {
				@Override
				public ErrorCode getErrorCode() {
					return super.getErrorCode();
				}
			};
		}
		this.score = score;
	}

	public void setPlay_time(int play_time) {

		// 정상적으로 가장 빨리 풀었을 때(all 찍기 포함) 걸리는 시간
		final int MIN_PLAYTIME = 800;

		if(play_time>=0 && play_time<=MIN_PLAYTIME){
			throw new CustomException(ErrorCode.GRAMMAR_CLEAR_TIME_UNAVAILABLE) {
				@Override
				public ErrorCode getErrorCode() {
					return super.getErrorCode();
				}
			};
		}

		this.play_time = play_time < 0
			           ? Integer.MAX_VALUE
			  		   : play_time;
	}

	@Override
	public String toString() {
		return "GrammarScoreReqDto{" +
			"score=" + score +
			", play_time=" + play_time +
			'}';
	}
}

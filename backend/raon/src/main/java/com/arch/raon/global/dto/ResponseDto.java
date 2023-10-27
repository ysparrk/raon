package com.arch.raon.global.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDto {
	private String message;
	private Object data;

	@Builder
	public ResponseDto(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	@Builder
	public ResponseDto(String message) {
		this.message = message;
	}
}

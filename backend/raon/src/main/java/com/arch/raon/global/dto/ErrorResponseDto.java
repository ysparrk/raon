package com.arch.raon.global.dto;

import com.arch.raon.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ErrorResponseDto {

    private String code;
    private String message;

    public static ErrorResponseDto of (ErrorCode code) {
        return new ErrorResponseDto(code.getCode(), code.getMessage());
    }
}


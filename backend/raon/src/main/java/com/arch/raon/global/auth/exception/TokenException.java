package com.arch.raon.global.auth.exception;

import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;

public class TokenException extends CustomException {
    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}

package com.submit.toyproject.rms_backend_springboot.exception.handler;

import lombok.Getter;

@Getter
public class RmsException extends RuntimeException {
    private final ErrorCode errorCode;

    public RmsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

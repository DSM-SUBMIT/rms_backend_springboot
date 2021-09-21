package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class InvalidTokenException extends RmsException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}

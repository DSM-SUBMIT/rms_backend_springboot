package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class InvalidUserTokenException extends RmsException {
    public InvalidUserTokenException() {
        super(ErrorCode.INVALID_USER_TOKEN);
    }
}

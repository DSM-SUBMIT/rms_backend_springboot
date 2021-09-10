package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class InvalidIdTokenInformationException extends RmsException {
    public InvalidIdTokenInformationException() {
        super(ErrorCode.INVALID_ID_TOKEN_INFORMATION);
    }
}

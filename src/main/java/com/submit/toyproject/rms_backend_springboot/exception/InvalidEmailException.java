package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class InvalidEmailException extends RmsException {
    public InvalidEmailException() {
        super (ErrorCode.INVALID_EMAIL);
    }
}

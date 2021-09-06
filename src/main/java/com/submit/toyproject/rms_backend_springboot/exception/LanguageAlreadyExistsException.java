package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class LanguageAlreadyExistsException extends RmsException {
    public LanguageAlreadyExistsException() {
        super(ErrorCode.LANGUAGE_ALREADY_EXISTS);
    }
}

package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class RmsJsonProcessingException extends RmsException {
    public RmsJsonProcessingException() {
        super(ErrorCode.JSON_PROCESSING);
    }
}

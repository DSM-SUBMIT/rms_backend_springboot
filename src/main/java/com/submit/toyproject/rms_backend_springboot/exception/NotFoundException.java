package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class NotFoundException extends RmsException {
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }
}

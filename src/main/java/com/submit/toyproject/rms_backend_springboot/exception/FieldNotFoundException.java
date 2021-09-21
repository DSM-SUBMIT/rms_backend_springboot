package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class FieldNotFoundException extends RmsException {
    public FieldNotFoundException() {
        super(ErrorCode.FIELD_NOT_FOUND);
    }
}

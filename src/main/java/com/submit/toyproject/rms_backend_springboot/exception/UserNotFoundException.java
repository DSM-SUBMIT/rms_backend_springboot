package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class UserNotFoundException extends RmsException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}

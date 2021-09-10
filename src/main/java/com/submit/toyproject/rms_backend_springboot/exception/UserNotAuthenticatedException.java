package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class UserNotAuthenticatedException extends RmsException {
    public UserNotAuthenticatedException() {
        super(ErrorCode.USER_NOT_AUTHENTICATED);
    }
}

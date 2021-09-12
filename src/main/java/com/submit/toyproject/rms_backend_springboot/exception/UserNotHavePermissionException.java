package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class UserNotHavePermissionException extends RmsException {
    public UserNotHavePermissionException() {
        super(ErrorCode.DO_NOT_HAVE_PERMISSION_WRITE);
    }
}

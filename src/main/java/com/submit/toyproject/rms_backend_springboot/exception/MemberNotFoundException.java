package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class MemberNotFoundException extends RmsException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

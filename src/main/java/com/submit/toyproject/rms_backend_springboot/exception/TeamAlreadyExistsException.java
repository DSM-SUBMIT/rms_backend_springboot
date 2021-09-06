package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class TeamAlreadyExistsException extends RmsException {
    public TeamAlreadyExistsException() {
        super(ErrorCode.TEAM_ALREADY_EXISTS);
    }
}

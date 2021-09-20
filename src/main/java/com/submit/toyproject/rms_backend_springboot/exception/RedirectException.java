package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class RedirectException extends RmsException {
    public RedirectException() {
        super(ErrorCode.REDIRECT_ERROR);
    }
}

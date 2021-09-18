package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class EmailSendFailException extends RmsException {
    public EmailSendFailException() {
        super(ErrorCode.EMAIL_SEND_FAIL);
    }
}

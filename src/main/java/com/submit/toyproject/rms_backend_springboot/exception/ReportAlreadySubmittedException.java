package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class ReportAlreadySubmittedException extends RmsException {
    public ReportAlreadySubmittedException() {
        super(ErrorCode.REPORT_ALREADY_SUBMITTED);
    }
}

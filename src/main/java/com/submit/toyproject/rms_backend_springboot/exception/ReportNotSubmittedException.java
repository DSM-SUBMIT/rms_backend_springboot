package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class ReportNotSubmittedException extends RmsException {
    public ReportNotSubmittedException() {
        super(ErrorCode.REPORT_NOT_SUBMITTED);
    }
}

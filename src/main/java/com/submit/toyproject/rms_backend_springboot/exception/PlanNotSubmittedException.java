package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class PlanNotSubmittedException extends RmsException {
    public PlanNotSubmittedException() {
        super(ErrorCode.PLAN_NOT_SUBMITTED);
    }
}

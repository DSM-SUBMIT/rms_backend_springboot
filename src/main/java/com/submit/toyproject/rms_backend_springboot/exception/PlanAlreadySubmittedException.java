package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class PlanAlreadySubmittedException extends RmsException {
    public PlanAlreadySubmittedException() {
        super(ErrorCode.PLAN_ALREADY_SUBMITTED);
    }
}

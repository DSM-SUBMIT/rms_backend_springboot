package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class PlanNotAcceptedException extends RmsException {
    public PlanNotAcceptedException() {
        super(ErrorCode.PLAN_NOT_ACCEPTED);
    }
}

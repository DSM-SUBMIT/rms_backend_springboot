package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class PlanNotFoundException extends RmsException {
    public PlanNotFoundException() {
        super(ErrorCode.PLAN_NOT_FOUND);
    }
}

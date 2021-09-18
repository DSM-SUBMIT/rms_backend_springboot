package com.submit.toyproject.rms_backend_springboot.exception.handler;

public class PlanNotFoundException extends RmsException {
    public PlanNotFoundException() {
        super(ErrorCode.PLAN_NOT_FOUND);
    }
}

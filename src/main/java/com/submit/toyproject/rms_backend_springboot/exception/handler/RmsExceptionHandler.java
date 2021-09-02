package com.submit.toyproject.rms_backend_springboot.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RmsExceptionHandler {

    @ExceptionHandler(RmsException.class)
    protected ResponseEntity<ErrorResponse> handleException(final RmsException exception) {
        final ErrorCode e = exception.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(e.getStatus(), e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

}

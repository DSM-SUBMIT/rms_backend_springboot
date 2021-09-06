package com.submit.toyproject.rms_backend_springboot.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_USER_TOKEN(401,"Invalid token"),
    INVALID_ADMIN_TOKEN(401,"Invalid token"),
    LANGUAGE_ALREADY_EXISTS(409, "Language is already exists.");

    private final int status;
    private final String message;

}

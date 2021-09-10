package com.submit.toyproject.rms_backend_springboot.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_USER_TOKEN(401,"Invalid token."),
    INVALID_ADMIN_TOKEN(401,"Invalid token."),
    TEAM_ALREADY_EXISTS(409, "Team name is already exists."),
    LANGUAGE_ALREADY_EXISTS(409, "Language is already exists."),
    USER_NOT_FOUND(404, "User not found."),
    INVALID_EMAIL(400, "Google email must end with '@dsm.hs.kr'."),
    INVALID_ID_TOKEN_INFORMATION(400, "Id_Token information does not match.");

    private final int status;
    private final String message;
}

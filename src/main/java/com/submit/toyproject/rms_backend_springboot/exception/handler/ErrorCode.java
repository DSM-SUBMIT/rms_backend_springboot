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
    INVALID_ID_TOKEN_INFORMATION(400, "Id_Token information does not match."),
    INVALID_EMAIL(400, "Google email must end with '@dsm.hs.kr'."),
    USER_NOT_FOUND(404, "User not found."),
    USER_NOT_AUTHENTICATED(401, "Not authenticated"),
    PLAN_ALREADY_SUBMITTED(400, "Plan is already submitted."),
    REPORT_NOT_FOUND(404, "Report is not found."),
    PLAN_NOT_FOUND(404, "Plan is not found."),
    DO_NOT_HAVE_PERMISSION_WRITE(403, "User don't have permission to write"),
    PROJECT_NOT_FOUND(404, "Project not found."),
    FIELD_NOT_FOUND(404, "Field not found."),
    PERMISSION_DENIED(403, "Permission Denied"),
    EMAIL_SEND_FAIL(500, "Email send fail."),
    REPORT_ALREADY_SUBMITTED(400, "Report is already submitted."),
    REDIRECT_ERROR(500, "Server error."),
    JSON_PROCESSING(500, "Json processing exception."),
    REPORT_ALREADY_SUBMITTED(400, "Report is already submitted."),
    EMAIL_SEND_FAIL(400, "Email was not sent properly."),
    PLAN_NOT_ACCEPTED(400, "This project's plan is not accepted.");

    private final int status;
    private final String message;
}

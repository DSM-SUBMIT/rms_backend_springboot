package com.submit.toyproject.rms_backend_springboot.dto.request;

import lombok.*;

@Data
@Builder
public class GoogleTokenRequest {

    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String grant_type;
}

package com.submit.toyproject.rms_backend_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleOauthResponse {

    private String access_token;

    private String expires_in;

    private String scope;

    private String token_type;

    private String id_token;

}

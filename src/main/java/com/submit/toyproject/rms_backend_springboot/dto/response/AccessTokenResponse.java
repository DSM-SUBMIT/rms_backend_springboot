package com.submit.toyproject.rms_backend_springboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessTokenResponse {

    @Schema(example = "액세스 토큰")
    private final String accessToken;

}
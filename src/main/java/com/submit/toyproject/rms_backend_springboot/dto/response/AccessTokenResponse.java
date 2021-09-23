package com.submit.toyproject.rms_backend_springboot.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessTokenResponse {

    @ApiModelProperty(example = "액세스 토큰")
    private final String accessToken;

}
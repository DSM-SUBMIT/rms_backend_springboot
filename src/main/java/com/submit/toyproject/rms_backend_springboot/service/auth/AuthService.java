package com.submit.toyproject.rms_backend_springboot.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.submit.toyproject.rms_backend_springboot.dto.response.AccessTokenResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.TokenResponse;

public interface AuthService {

    String getGoogleLink();
    TokenResponse requestTokenByCode(String code) throws JsonProcessingException;
    AccessTokenResponse tokenRefresh(String token);
}

package com.submit.toyproject.rms_backend_springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.submit.toyproject.rms_backend_springboot.dto.response.AccessTokenResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.TokenResponse;
import com.submit.toyproject.rms_backend_springboot.service.auth.AuthService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final HttpServletResponse response;

    @ApiOperation(value = "구글 로그인으로 리다이렉트")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/login")
    public void googleLogin() throws IOException {
        response.sendRedirect(authService.getGoogleLink());
    }

    @ApiOperation(value = "액세스 토큰과 리프레시 토큰 반환", notes = "구글 로그인 성공 시 이곳으로 리다이렉트 되고 액세스 토큰 & 리프레시 토큰 반환")
    @GetMapping("/google/callback")
    public TokenResponse requestTokenByCode(@RequestParam String code) throws JsonProcessingException {
        return authService.requestTokenByCode(code);
    }

    @ApiOperation(value = "토큰 리프레시")
    @ApiImplicitParam(name = "token", value = "리프레시 토큰")
    @PutMapping("/token")
    public AccessTokenResponse accessTokenRefresh(@RequestHeader(name = "X-Refresh-Token") String token) {
        return authService.tokenRefresh(token);
    }

}

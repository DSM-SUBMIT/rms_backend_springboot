package com.submit.toyproject.rms_backend_springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.submit.toyproject.rms_backend_springboot.dto.response.AccessTokenResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.TokenResponse;
import com.submit.toyproject.rms_backend_springboot.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Tag(name = "인증")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final HttpServletResponse response;

    @Operation(summary = "구글 로그인으로 리다이렉트")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/login")
    public void googleLogin() throws IOException {
        response.sendRedirect(authService.getGoogleLink());
    }

    @Operation(summary = "액세스 토큰과 리프레시 토큰 반환")
    @PostMapping("/token")
    public TokenResponse requestTokenByCode(@RequestParam String code) throws JsonProcessingException {
        return authService.requestTokenByCode(code);
    }

    @Operation(summary = "토큰 리프레시")
    @Parameter(name = "X-Refresh-Token", in = ParameterIn.HEADER, description = "리프레시 토큰")
    @PutMapping("/token")
    public AccessTokenResponse accessTokenRefresh(@RequestHeader(name = "X-Refresh-Token") String token) {
        return authService.tokenRefresh(token);
    }

}

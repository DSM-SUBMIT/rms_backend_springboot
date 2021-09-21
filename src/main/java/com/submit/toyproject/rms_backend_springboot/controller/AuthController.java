package com.submit.toyproject.rms_backend_springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.submit.toyproject.rms_backend_springboot.dto.response.AccessTokenResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.TokenResponse;
import com.submit.toyproject.rms_backend_springboot.service.auth.AuthService;
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

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/login")
    public void googleLogin() throws IOException {
        response.sendRedirect(authService.getGoogleLink());
    }

    @GetMapping("/google/callback")
    public TokenResponse requestTokenByCode(@RequestParam String code) throws JsonProcessingException {
        return authService.requestTokenByCode(code);
    }

    @PutMapping("/token")
    public AccessTokenResponse accessTokenRefresh(@RequestHeader(name = "X-Refresh-Token") String token) {
        return authService.tokenRefresh(token);
    }

}

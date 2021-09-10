package com.submit.toyproject.rms_backend_springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.submit.toyproject.rms_backend_springboot.dto.response.TokenResponse;
import com.submit.toyproject.rms_backend_springboot.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final HttpServletResponse response;

    @GetMapping("/google")
    public void googleLogin() {
        try {
            String redirectURL = authService.getGoogleLink();
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/google/callback")
    public TokenResponse requestTokenByCode(@RequestParam String code) {
        try {
            return authService.requestTokenByCode(code);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/token")
    public String accessTokenRefresh(@RequestHeader(name = "X-Refresh-Token") String token) {
        return authService.tokenRefresh(token);
    }

}

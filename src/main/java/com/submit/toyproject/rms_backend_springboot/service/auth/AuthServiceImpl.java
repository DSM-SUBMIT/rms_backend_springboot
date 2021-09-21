package com.submit.toyproject.rms_backend_springboot.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.submit.toyproject.rms_backend_springboot.domain.refreshToken.RefreshToken;
import com.submit.toyproject.rms_backend_springboot.domain.refreshToken.RefreshTokenRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.oauth.GoogleTokenRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.AccessTokenResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.TokenResponse;
import com.submit.toyproject.rms_backend_springboot.exception.InvalidEmailException;
import com.submit.toyproject.rms_backend_springboot.exception.InvalidIdTokenInformationException;
import com.submit.toyproject.rms_backend_springboot.exception.InvalidUserTokenException;
import com.submit.toyproject.rms_backend_springboot.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    @Value("${oauth.google.url}")
    private String GOOGLE_BASE_URL;

    @Value("${oauth.google.client.id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${oauth.google.redirect.url}")
    private String GOOGLE_REDIRECT_URL;

    @Value("${oauth.google.client.secret}")
    private String GOOGLE_CLIENT_SECRET;

    @Value("${jwt.refresh.exp}")
    private Long refreshExp;

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final GoogleOauthClient googleOauthClient;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String getGoogleLink() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "email profile");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_REDIRECT_URL);

        String paramString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GOOGLE_BASE_URL + "?" + paramString;
    }

    @Override
    public TokenResponse requestTokenByCode(String code) throws JsonProcessingException {

        GoogleTokenRequest googleTokenRequest = GoogleTokenRequest.builder()
                .client_id(GOOGLE_CLIENT_ID)
                .client_secret(GOOGLE_CLIENT_SECRET)
                .code(code)
                .redirect_uri(GOOGLE_REDIRECT_URL)
                .grant_type("authorization_code")
                .build();

        Map<String, String> userInfo = googleOauthClient.requestUserInfo(googleTokenRequest);

        String iss = userInfo.get("iss");
        String email = userInfo.get("email");
        String name = userInfo.get("name");
        String hd = userInfo.get("hd");
        String aud = userInfo.get("aud");
        long exp = Long.parseLong(userInfo.get("exp")) * 1000;

        // ID 토큰 유효성 검사 4단계 & 학교 이메일 검사
        if(hd == null || !hd.equals("dsm.hs.kr")) {
            throw new InvalidEmailException();
        } else if(!(iss.equals("https://accounts.google.com") || iss.equals("accounts.google.com"))
                || !aud.equals(GOOGLE_CLIENT_ID) || exp < System.currentTimeMillis()) {
            throw new InvalidIdTokenInformationException();
        }

        RefreshToken refreshToken = refreshTokenRepository.save(
                RefreshToken.builder()
                        .refreshExp(refreshExp)
                        .refreshToken(jwtTokenProvider.generateRefreshToken(email))
                        .email(email)
                        .build());

        if(userRepository.findByEmail(email).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(email)
                            .name(name)
                            .build()
            );
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(email))
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    @Override
    public AccessTokenResponse tokenRefresh(String token) {
        if (!jwtTokenProvider.isRefreshToken(token)) throw new InvalidUserTokenException();

        return refreshTokenRepository.findByRefreshToken(token)
                .map(refreshToken -> refreshToken.update(refreshExp))
                .map(refreshToken -> new AccessTokenResponse(jwtTokenProvider.generateAccessToken(refreshToken.getEmail())))
                .orElseThrow(InvalidUserTokenException::new);
    }
}

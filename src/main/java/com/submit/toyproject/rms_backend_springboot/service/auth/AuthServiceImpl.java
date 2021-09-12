package com.submit.toyproject.rms_backend_springboot.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.request.GoogleTokenRequest;
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

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final GoogleOauthClient googleOauthClient;

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

        GoogleTokenRequest googleTokenRequest = GoogleTokenRequest
                .builder()
                .client_id(GOOGLE_CLIENT_ID)
                .client_secret(GOOGLE_CLIENT_SECRET)
                .code(code)
                .redirect_uri(GOOGLE_REDIRECT_URL)
                .grant_type("authorization_code").build();

        Map<String, String> userInfo = googleOauthClient.requestUserInfo(googleTokenRequest);

        String iss = userInfo.get("iss");
        String email = userInfo.get("email");
        String name = userInfo.get("name");
        String hd = userInfo.get("hd");
        String aud = userInfo.get("aud");
        Long exp = Long.parseLong(userInfo.get("exp")) * 1000;

        // ID 토큰 유효성 검사 4단계 & 학교 이메일 검사
        if(hd == null || !hd.equals("dsm.hs.kr")) {
            log.info(hd);
            throw new InvalidEmailException();
        } else if(!(iss.equals("https://accounts.google.com") || iss.equals("accounts.google.com"))
                || !aud.equals(GOOGLE_CLIENT_ID) || exp < System.currentTimeMillis()) {
            log.info(iss);
            log.info(aud);
            log.info(exp + " & " + System.currentTimeMillis());
            throw new InvalidIdTokenInformationException();
        }

        if(userRepository.findByEmail(email).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(email)
                            .name(name)
                            .build()
            );
        }

        return new TokenResponse(jwtTokenProvider.generateAccessToken(email),
                jwtTokenProvider.generateRefreshToken(email));
    }

    @Override
    public String tokenRefresh(String token) {
        if (!jwtTokenProvider.isRefreshToken(token)) throw new InvalidUserTokenException();
        String email = jwtTokenProvider.getBody(token).getSubject();
        return jwtTokenProvider.generateAccessToken(email);
    }
}
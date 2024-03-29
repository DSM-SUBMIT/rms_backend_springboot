package com.submit.toyproject.rms_backend_springboot.security.jwt;

import com.submit.toyproject.rms_backend_springboot.exception.InvalidUserTokenException;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthDetailService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access.exp}")
    private Long accessExp;

    @Value("${jwt.refresh.exp}")
    private Long refreshExp;

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    private final AuthDetailService authDetailService;

    public String generateAccessToken(String email) {
        return generateToken(email, "access", accessExp);
    }

    public String generateRefreshToken(String email) {
        return generateToken(email, "refresh", refreshExp);
    }

    private String generateToken(String email, String type, Long exp) {
        return Jwts.builder()
                .setHeaderParam("typ", type)
                .setIssuedAt(new Date())
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isRefreshToken(String token) {
        try {
            return getClaims(token).getHeader().get("typ").equals("refresh");
        } catch (Exception e) {
            throw new InvalidUserTokenException();
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailService.loadUserByUsername(this.getId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getId(String token) {
        return getClaims(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER);
        if (bearerToken != null && bearerToken.startsWith(PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String jwtToken) {
        try {
            return getClaims(jwtToken).getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            throw new InvalidUserTokenException();
        }
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token);
    }

}
package com.submit.toyproject.rms_backend_springboot.config;

import com.submit.toyproject.rms_backend_springboot.security.jwt.JwtAuthenticationFilter;
import com.submit.toyproject.rms_backend_springboot.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;
    private final CorsFilter corsFilter;

    @Override
    public void configure(HttpSecurity security) {
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtTokenProvider);

        security.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        security.addFilterBefore(corsFilter, JwtAuthenticationFilter.class);
    }

}

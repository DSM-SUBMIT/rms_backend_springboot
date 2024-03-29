package com.submit.toyproject.rms_backend_springboot.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpMethod.OPTIONS;

@Component
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "https://user.dsm-rms.com");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, PATCH, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Refresh-Token, withCredentials");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");

        if (OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}

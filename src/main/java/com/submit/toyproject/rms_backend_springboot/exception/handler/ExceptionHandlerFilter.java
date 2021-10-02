package com.submit.toyproject.rms_backend_springboot.exception.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
            try {
                chain.doFilter(request, response);
            } catch(Exception e) {
                e.printStackTrace();
            }
    }

}

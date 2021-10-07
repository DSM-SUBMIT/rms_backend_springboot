package com.submit.toyproject.rms_backend_springboot.exception.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
            try {
                chain.doFilter(request, response);
            } catch(RmsException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
                response.setContentType("application/json");
                response.setStatus(errorResponse.getStatus());
                response.getWriter().write(errorResponse.toString());
            }
    }

}

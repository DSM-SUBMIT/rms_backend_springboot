package com.submit.toyproject.rms_backend_springboot.security.auth;

import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.exception.InvalidUserTokenException;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationFacade {

    private final UserRepository userRepository;

    public String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UserNotAuthenticatedException();
        }
        return authentication.getName();
    }

    public User certifiedUser() {
        return userRepository.findByEmail(getUserEmail())
                .orElseThrow(InvalidUserTokenException::new);
    }

}

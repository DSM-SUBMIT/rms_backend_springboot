package com.submit.toyproject.rms_backend_springboot.service.user;

import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.response.UserDto;
import com.submit.toyproject.rms_backend_springboot.dto.response.UsersResponse;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotAuthenticatedException;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public UsersResponse getUsers(String name) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotAuthenticatedException::new);
        List<User> users = userRepository.findByNameLike("%" + name + "%");
        return new UsersResponse(users.stream().map(
                user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build()
        ).collect(Collectors.toList()));
    }

}

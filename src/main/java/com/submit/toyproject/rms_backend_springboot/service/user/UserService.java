package com.submit.toyproject.rms_backend_springboot.service.user;

import com.submit.toyproject.rms_backend_springboot.dto.response.UsersResponse;

public interface UserService {
    UsersResponse getUsers(String name);
}

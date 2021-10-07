package com.submit.toyproject.rms_backend_springboot.service.user;

import com.submit.toyproject.rms_backend_springboot.dto.request.NumberRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.MyPageResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.NameResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.UsersResponse;

public interface UserService {
    UsersResponse getUsers(String name);
    MyPageResponse getMyPage();
    NameResponse getName();
    void saveNumber(NumberRequest request);
}

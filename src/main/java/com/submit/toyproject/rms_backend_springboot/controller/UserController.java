package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.response.MyPageResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.UsersResponse;
import com.submit.toyproject.rms_backend_springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public UsersResponse getUsers(@RequestParam(defaultValue = "%%") String name) {
        return userService.getUsers(name);
    }

    @GetMapping("/me")
    public MyPageResponse getMyPage() {
        return userService.getMyPage();
    }

}

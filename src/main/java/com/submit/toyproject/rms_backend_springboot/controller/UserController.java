package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.response.MyPageResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.NameResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.UsersResponse;
import com.submit.toyproject.rms_backend_springboot.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    @Operation(summary = "유저 리스트 받아오기", description = "프로젝트 생성 시 멤버 추가를 위한 유저 목록입니다. 이름으로 검색할 수 있습니다.")
    public UsersResponse getUsers(@RequestParam(defaultValue = "%%") String name) {
        return userService.getUsers(name);
    }

    @Operation(summary = "마이페이지 정보가져오기")
    @GetMapping("/me")
    public MyPageResponse getMyPage() {
        return userService.getMyPage();
    }

    @Operation(summary = "헤더에서 사용될 사용자 이름 불러오기")
    @GetMapping("/name")
    public NameResponse getName() {
        return userService.getName();
    }

}

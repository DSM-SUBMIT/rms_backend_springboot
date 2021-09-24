package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.response.MyPageResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.UsersResponse;
import com.submit.toyproject.rms_backend_springboot.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"유저"})
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    @ApiOperation(value = "유저 리스트 받아오기", notes = "프로젝트 생성 시 멤버 추가를 위한 유저 목록입니다. 이름으로 검색할 수 있습니다.")
    public UsersResponse getUsers(@RequestParam(defaultValue = "%%") String name) {
        return userService.getUsers(name);
    }

    @ApiOperation(value = "마이페이지 정보가져오기")
    @GetMapping("/me")
    public MyPageResponse getMyPage() {
        return userService.getMyPage();
    }

}

package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;
import com.submit.toyproject.rms_backend_springboot.service.main.MainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"메인 피드"})
@RequiredArgsConstructor
@RestController
public class MainController {

    private final MainService mainService;

    @ApiOperation(value = "메인 피드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "?page=&size= 이렇게 하시면 됩니다. ※ 페이지 1부터 시작"),
            @ApiImplicitParam(name = "size", value = "사이즈"),
            @ApiImplicitParam(name = "field", value = "필터링, ','로 구분, 허용 값 : WEB, APP, GAME, EMBEDDED, AI_BIGDATA, SECURITY")
    })
    @GetMapping
    public MainFeedResponse getMainFeed(Pageable page, @RequestParam(required = false) List<String> field) {
        return mainService.getMainFeed(page, field);
    }

}

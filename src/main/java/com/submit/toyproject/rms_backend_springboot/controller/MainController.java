package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;
import com.submit.toyproject.rms_backend_springboot.service.main.MainService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final MainService mainService;

    @ApiOperation(value = "메인 피드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page, size", value = "?page=&size= 이렇게 하시면 됩니다~"),
            @ApiImplicitParam(name = "field", value = "분야로 필터링, 여러개이면 ','로 구분, 없으면 안넣어도 됨")
    })
    @GetMapping
    public MainFeedResponse getMainFeed(Pageable page, @RequestParam(required = false) List<FieldEnum> field) {
        return mainService.getMainFeed(page, field);
    }

}

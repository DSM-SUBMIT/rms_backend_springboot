package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;
import com.submit.toyproject.rms_backend_springboot.service.main.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "메인 피드")
@RequiredArgsConstructor
@RestController
public class MainController {

    private final MainService mainService;

    @Operation(summary = "메인 피드")
    @Parameter(name = "field", description = "필터링, ','로 구분, 허용 값 : WEB, APP, GAME, EMBEDDED, AI_BIGDATA, SECURITY")
    @GetMapping
    public MainFeedResponse getMainFeed(@ParameterObject @PageableDefault(page = 1, size = 5) Pageable page, @RequestParam(required = false) List<String> field) {
        return mainService.getMainFeed(page, field);
    }

}

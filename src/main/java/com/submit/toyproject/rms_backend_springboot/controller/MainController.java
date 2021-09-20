package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedProjectDetailResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;
import com.submit.toyproject.rms_backend_springboot.service.main.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class MainController {

    private final MainService mainService;

    @GetMapping
    public MainFeedResponse getMainFeed(
            @RequestParam Integer page, @RequestParam int size, @RequestParam(required = false) List<FieldEnum> field) {
        return mainService.getMainFeed(page, size, field);
    }

}
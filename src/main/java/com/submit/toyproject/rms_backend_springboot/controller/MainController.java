package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;
import com.submit.toyproject.rms_backend_springboot.service.main.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class MainController {

    private final MainService mainService;

    @GetMapping
    public MainFeedResponse getMainFeed(
            @RequestParam Integer lastProjectId, @RequestParam int size) {
        return mainService.getMainFeed(lastProjectId, size);
    }
}

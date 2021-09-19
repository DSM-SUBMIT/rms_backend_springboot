package com.submit.toyproject.rms_backend_springboot.service.main;

import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;

public interface MainService {
    MainFeedResponse getMainFeed(Integer lastProjectId, int size);
}

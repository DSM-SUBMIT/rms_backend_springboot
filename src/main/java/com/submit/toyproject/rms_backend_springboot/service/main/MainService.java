package com.submit.toyproject.rms_backend_springboot.service.main;

import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MainService {
    MainFeedResponse getMainFeed(Pageable page, List<String> filteringList);
}

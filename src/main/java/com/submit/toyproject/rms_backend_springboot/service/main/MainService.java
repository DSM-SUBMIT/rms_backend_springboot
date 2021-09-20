package com.submit.toyproject.rms_backend_springboot.service.main;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;

import java.util.List;

public interface MainService {
    MainFeedResponse getMainFeed(Integer page, int size, List<FieldEnum> filteringList);
}

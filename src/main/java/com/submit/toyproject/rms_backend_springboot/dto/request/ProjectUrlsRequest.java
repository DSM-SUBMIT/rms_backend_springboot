package com.submit.toyproject.rms_backend_springboot.dto.request;

import lombok.Getter;

@Getter
public class ProjectUrlsRequest {
    private String githubUrl;
    private String docsUrl;
    private String serviceUrl;
}

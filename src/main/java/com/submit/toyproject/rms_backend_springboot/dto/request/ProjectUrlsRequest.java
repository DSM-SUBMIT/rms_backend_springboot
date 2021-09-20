package com.submit.toyproject.rms_backend_springboot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUrlsRequest {
    private String githubUrl;
    private String docsUrl;
    private String serviceUrl;
}

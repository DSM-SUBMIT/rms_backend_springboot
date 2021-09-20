package com.submit.toyproject.rms_backend_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageProjectDetailResponse {

    private Integer id;

    private String projectType;

    private String projectName;

    private List<String> fieldList;

    private String teamName;

    private Boolean isReportAccepted;

    private Boolean isReportSubmitted;

    private Boolean isPlanAccepted;

    private Boolean isPlanSubmitted;

    private List<ProjectMemberDto> memberList;

    private String githubUrl;

    private String serviceUrl;

    private String docsUrl;

}

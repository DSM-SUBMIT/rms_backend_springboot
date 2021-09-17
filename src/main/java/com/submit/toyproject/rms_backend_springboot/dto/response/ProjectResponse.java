package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Integer id;

    private String projectType;

    private String projectName;

    private List<String> fieldList;

    private String teamName;

    private List<MemberResponse> memberList;

    private String githubUrl;

    private String serviceUrl;

    private String docsUrl;

}

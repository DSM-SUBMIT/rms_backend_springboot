package com.submit.toyproject.rms_backend_springboot.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
public class ProjectRequest {

    @NotNull
    private String projectName;

    @NotNull
    private String teamName;

    @NotNull
    private String techStacks;

    @NotNull
    private String projectType;

    @NotNull
    private String teacher;

    private String githubUrl;

    private String docsUrl;

    private String serviceUrl;

    @NotNull
    private List<String> fieldList;

    private List<Map<String, String>> memberList;
}

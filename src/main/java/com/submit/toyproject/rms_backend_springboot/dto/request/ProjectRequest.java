package com.submit.toyproject.rms_backend_springboot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    @NotNull
    private String projectName;

    @NotNull
    private String teamName;

    @NotNull
    private String techStacks;

    @NotNull
    private ProjectType projectType;

    @NotNull
    private String teacher;

    private String githubUrl;
    @NotNull
    private List<FieldEnum> fieldList;

    private String docsUrl;
    @NotNull
    private List<ProjectMemberDto> memberList;
}

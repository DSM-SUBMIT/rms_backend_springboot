package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainFeedProjectDetailResponse {

    @Schema(description = "프로젝트 Id", example = "1")
    private Integer id;

    @Schema(description = "프로젝트 타입", example = "동아리 프로젝트"
            , allowableValues = "동아리 프로젝트, 팀 프로젝트, 개인 프로젝트, 프로젝트 실무 1, 프로젝트 실무 2, 소프트웨어 공학")
    private String projectType;

    @Schema(description = "프로젝트 이름", example = "RMS")
    private String projectName;

    @Schema(description = "기술 스택", example = "Java, Python")
    private String techStack;

    @Schema(description = "분야 리스트", example = "[WEB, APP, EMBEDDED]")
    private List<FieldEnum> fieldList;

    @Schema(description = "팀 이름", example = "SUBMIT")
    private String teamName;

    @Schema(description = "멤버 리스트")
    private List<ProjectMemberDto> memberList;

    @Schema(description = "깃허브 url", example = "https://www.github.com/domythang, https://github.com", nullable = true)
    private String githubUrl;

    @Schema(description = "서비스 url", example = "http://pow-submit.com", nullable = true)
    private String serviceUrl;

    @Schema(description = "문서 url", example = "https:///notion.so", nullable = true)
    private String docsUrl;

    public static MainFeedProjectDetailResponse of(Project project, List<FieldEnum> fieldList, List<ProjectMemberDto> memberList) {
        return MainFeedProjectDetailResponse.builder()
                .id(project.getId())
                .projectType(project.getProjectType().getDivision())
                .projectName(project.getProjectName())
                .fieldList(fieldList)
                .teamName(project.getTeamName())
                .techStack(project.getTechStacks())
                .memberList(memberList)
                .githubUrl(project.getGithubUrl())
                .serviceUrl(project.getServiceUrl())
                .docsUrl(project.getDocsUrl())
                .build();
    }
}

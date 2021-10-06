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
public class MyPageProjectDetailResponse {

    @Schema(description = "프로젝트 아이디", example = "1")
    private Integer id;

    @Schema(description = "프로젝트 타입", example = "TEAM")
    private String projectType;

    @Schema(description = "프로젝트 이름", example = "POW")
    private String projectName;

    @Schema(description = "분야 리스트", example = "[WEB, APP, EMBEDDED]")
    private List<FieldEnum> fieldList;

    @Schema(description = "기술 스택", example = "Java, Python")
    private String techStack;

    @Schema(description = "팀 이름", example = "SUBMIT")
    private String teamName;

    @Schema(description = "보고서 승인 여부", example = "null")
    private Boolean isReportAccepted;

    @Schema(description = "보고서 제출 여부", example = "false")
    private Boolean isReportSubmitted;

    @Schema(description = "계획서 승인 여부", example = "true")
    private Boolean isPlanAccepted;

    @Schema(description = "계획서 제출 여부", example = "true")
    private Boolean isPlanSubmitted;

    @Schema(description = "멤버 리스트")
    private List<ProjectMemberDto> memberList;

    @Schema(description = "깃허브 url", example = "https://www.github.com/domythang, https://github.com", nullable = true)
    private String githubUrl;

    @Schema(description = "서비스 url", example = "http://rms-rsm.com", nullable = true)
    private String serviceUrl;

    @Schema(description = "문서 url", example = "https:///notion.so", nullable = true)
    private String docsUrl;

    public static MyPageProjectDetailResponse of(Project project, List<FieldEnum> fieldList, List<ProjectMemberDto> memberList) {
        return MyPageProjectDetailResponse.builder()
                .id(project.getId())
                .projectType(project.getProjectType().getDivision())
                .projectName(project.getProjectName())
                .fieldList(fieldList)
                .teamName(project.getTeamName())
                .techStack(project.getTechStacks())
                .isPlanSubmitted(project.getStatus().getIsPlanSubmitted())
                .isPlanAccepted(project.getStatus().getIsPlanAccepted())
                .isReportSubmitted(project.getStatus().getIsReportSubmitted())
                .isReportAccepted(project.getStatus().getIsReportAccepted())
                .memberList(memberList)
                .githubUrl(project.getGithubUrl())
                .serviceUrl(project.getServiceUrl())
                .docsUrl(project.getDocsUrl())
                .build();
    }
}

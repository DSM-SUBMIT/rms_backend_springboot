package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "프로젝트 아이디", example = "1")
    private Integer id;

    @ApiModelProperty(value = "프로젝트 타입", example = "TEAM")
    private String projectType;

    @ApiModelProperty(value = "프로젝트 이름", example = "POW")
    private String projectName;

    @ApiModelProperty(value = "분야 리스트", example = "[WEB, APP, EMBEDDED]")
    private List<FieldEnum> fieldList;

    @ApiModelProperty(value = "기술 스택", example = "Java, Python")
    private String techStack;

    @ApiModelProperty(value = "팀 이름", example = "SUBMIT")
    private String teamName;

    @ApiModelProperty(value = "보고서 승인 여부", example = "null")
    private Boolean isReportAccepted;

    @ApiModelProperty(value = "보고서 제출 여부", example = "false")
    private Boolean isReportSubmitted;

    @ApiModelProperty(value = "계획서 승인 여부", example = "true")
    private Boolean isPlanAccepted;

    @ApiModelProperty(value = "계획서 제출 여부", example = "true")
    private Boolean isPlanSubmitted;

    @ApiModelProperty(value = "멤버 리스트")
    private List<ProjectMemberDto> memberList;

    @ApiModelProperty(value = "깃허브 url", example = "https://www.github.com/domythang, https://github.com", allowEmptyValue = true)
    private String githubUrl;

    @ApiModelProperty(value = "서비스 url", example = "http://rms-rsm.com", allowEmptyValue = true)
    private String serviceUrl;

    @ApiModelProperty(value = "문서 url", example = "https:///notion.so", allowEmptyValue = true)
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

package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListElementDto {

    @Schema(description = "프로젝트 아이디", example = "1")
    private Integer id;

    @Schema(description = "프로젝트 이름", example = "RMS")
    private String projectName;

    @Schema(description = "팀 이름", example = "Submit")
    private String teamName;

    @Schema(description = "프로젝트 타입", example = "동아리 프로젝트"
            , allowableValues = "동아리 프로젝트, 팀 프로젝트, 개인 프로젝트, 프로젝트 실무 1, 프로젝트 실무 2, 소프트웨어 공학")
    private String projectType;

    @Schema(description = "분야 리스트", example = "[WEB, APP, EMBEDDED]")
    private List<FieldEnum> fieldList;

    public static ProjectListElementDto of(Project project, List<FieldEnum> fieldList) {
        return ProjectListElementDto.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .teamName(project.getTeamName())
                .projectType(project.getProjectType().getDivision())
                .fieldList(fieldList)
                .build();
    }

}

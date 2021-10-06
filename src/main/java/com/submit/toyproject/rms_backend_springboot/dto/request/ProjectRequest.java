package com.submit.toyproject.rms_backend_springboot.dto.request;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectType;
import com.submit.toyproject.rms_backend_springboot.dto.response.ProjectMemberDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    @Schema(name = "프로젝트 이름", example = "RMS")
    @NotNull
    private String projectName;

    @Schema(name = "팀 이름", example = "Submit")
    @NotNull
    private String teamName;

    @Schema(name = "기술 스택1, 기술 스택2", example = "Java, Spring boot")
    @NotNull
    private String techStacks;

    @Schema(name = "프로젝트 타입", example = "CLUB")
    @NotNull
    private ProjectType projectType;

    @Schema(name = "선생님 성함", example = "양은정")
    @NotNull
    private String teacher;

    @Schema(name = "필드 리스트(Enum)", example = "[WEB, APP]")
    @NotNull
    private List<FieldEnum> fieldList;

    @Schema(name = "멤버 리스트")
    @NotNull
    private List<ProjectMemberDto> memberList;
}

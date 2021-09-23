package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListElementDto {

    @ApiModelProperty(value = "프로젝트 아이디", example = "1")
    private Integer id;

    @ApiModelProperty(value = "프로젝트 이름", example = "RMS")
    private String projectName;

    @ApiModelProperty(value = "팀 이름", example = "Submit")
    private String teamName;

    @ApiModelProperty(value = "프로젝트 타입", example = "동아리 프로젝트"
            , allowableValues = "동아리 프로젝트, 팀 프로젝트, 개인 프로젝트, 프로젝트 실무 1, 프로젝트 실무 2, 소프트웨어 공학")
    private String projectType;

    @ApiModelProperty(value = "분야 리스트", example = "[WEB, APP, EMBEDDED]")
    private List<FieldEnum> fieldList;

}

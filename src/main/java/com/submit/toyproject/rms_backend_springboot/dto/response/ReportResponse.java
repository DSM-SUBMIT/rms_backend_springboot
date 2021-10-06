package com.submit.toyproject.rms_backend_springboot.dto.response;

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
public class ReportResponse {

    @ApiModelProperty(example = "<팀여부>")
    private Boolean isTeam;

    @ApiModelProperty(example = "<작성자>")
    private String writer;

    @ApiModelProperty(example = "<프로젝트명>")
    private String projectName;

    @ApiModelProperty(example = "<보고서 내용>")
    private String content;

}

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

    @ApiModelProperty(example = "<작성자>")
    private String writer;

    @ApiModelProperty(example = "<프로젝트 분야 목록>")
    private List<String> field;

    @ApiModelProperty(example = "<보고서 내용>")
    private String content;

}

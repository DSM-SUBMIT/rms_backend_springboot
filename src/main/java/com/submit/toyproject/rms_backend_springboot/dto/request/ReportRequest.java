package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {

    @ApiModelProperty(example = "<보고서 내용>")
    @Size(max = 15000)
    @NotBlank
    private String content;

    @ApiModelProperty(example = "<영상 url>")
    private String videoUrl;

}

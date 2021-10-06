package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {

    @Schema(example = "<보고서 내용>")
    @Size(max = 15000)
    @NotNull
    private String content;

    @Schema(example = "<영상 url>")
    private String videoUrl;

}

package com.submit.toyproject.rms_backend_springboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {

    @Schema(example = "<팀여부>")
    private Boolean isTeam;

    @Schema(example = "<작성자>")
    private String writer;

    @Schema(example = "<프로젝트명>")
    private String projectName;

    @Schema(example = "<보고서 내용>")
    private String content;

}

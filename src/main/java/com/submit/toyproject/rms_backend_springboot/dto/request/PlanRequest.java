package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {

    @Schema(example = "<프로젝트 목표>")
    @NotNull
    private String goal;

    @Schema(example = "<프로젝트 내용>")
    @NotNull
    private String content;

    @Schema(example = "<결과 보고서 제출 여부>")
    @NotNull
    private Boolean includeResultReport;

    @Schema(example = "<프로그램 코드 제출 여부>")
    @NotNull
    private Boolean includeCode;

    @Schema(example = "<실행물 제출 여부>")
    @NotNull
    private Boolean includeOutcome;

    @Schema(example = "<기타>")
    private String includeOthers;

    @Schema(example = "<프로젝트 시작 예정일>")
    @Size(min = 7, max = 7)
    @NotNull
    private String plannedStartDate;

    @Schema(example = "<프로젝트 완료 예정일>")
    @Size(min = 7, max = 7)
    @NotNull
    private String plannedEndDate;

}

package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {

    @ApiModelProperty(example = "<프로젝트 목표>")
    @NotBlank
    private String goal;

    @ApiModelProperty(example = "<프로젝트 내용>")
    @NotBlank
    private String content;

    @ApiModelProperty(example = "<결과 보고서 제출 여부>")
    @NotNull
    private Boolean includeResultReport;

    @ApiModelProperty(example = "<프로그램 코드 제출 여부>")
    @NotNull
    private Boolean includeCode;

    @ApiModelProperty(example = "<실행물 제출 여부>")
    @NotNull
    private Boolean includeOutcome;

    @ApiModelProperty(example = "<기타>")
    private String includeOthers;

    @ApiModelProperty(example = "<프로젝트 시작 예정일>")
    @Size(min = 7, max = 7)
    @NotBlank
    private String plannedStartDate;

    @ApiModelProperty(example = "<프로젝트 완료 예정일>")
    @Size(min = 7, max = 7)
    @NotBlank
    private String plannedEndDate;

}

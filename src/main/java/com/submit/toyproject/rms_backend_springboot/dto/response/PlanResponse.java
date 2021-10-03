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
public class PlanResponse {

    @ApiModelProperty(example = "<프로젝트 이름>")
    private String projectName;

    @ApiModelProperty(example = "<프로젝트 예정 시작일>")
    private String plannedStartDate;

    @ApiModelProperty(example = "<프로젝트 예정 완료일>")
    private String plannedEndDate;

    @ApiModelProperty(example = "<멤버 목록>")
    private List<MemberDto> members;

    @ApiModelProperty(example = "<프로젝트 목표>")
    private String goal;

    @ApiModelProperty(example = "<프로젝트 내용>")
    private String content;

    @ApiModelProperty(example = "<결과 보고서 제출 여부>")
    private Boolean includeResultReport;

    @ApiModelProperty(example = "<프로그램 코드 제출 여부>")
    private Boolean includeCode;

    @ApiModelProperty(example = "<실행물 제출 여부>")
    private Boolean includeOutcome;

    @ApiModelProperty(example = "<기타>")
    private String includeOthers;

    @ApiModelProperty(example = "<작성자>")
    private String writer;

}

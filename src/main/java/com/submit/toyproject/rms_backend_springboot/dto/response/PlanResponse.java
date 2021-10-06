package com.submit.toyproject.rms_backend_springboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "<프로젝트 이름>")
    private String projectName;

    @Schema(example = "<프로젝트 예정 시작일>")
    private String plannedStartDate;

    @Schema(example = "<프로젝트 예정 완료일>")
    private String plannedEndDate;

    @Schema(example = "<멤버 목록>")
    private List<MemberDto> members;

    @Schema(example = "<프로젝트 목표>")
    private String goal;

    @Schema(example = "<프로젝트 내용>")
    private String content;

    @Schema(example = "<결과 보고서 제출 여부>")
    private Boolean includeResultReport;

    @Schema(example = "<프로그램 코드 제출 여부>")
    private Boolean includeCode;

    @Schema(example = "<실행물 제출 여부>")
    private Boolean includeOutcome;

    @Schema(example = "<기타>")
    private String includeOthers;

    @Schema(example = "<작성자>")
    private String writer;

}

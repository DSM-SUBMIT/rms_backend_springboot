package com.submit.toyproject.rms_backend_springboot.dto.response;

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

    private String projectName;

    private String plannedStartDate;

    private String plannedEndDate;

    private List<MemberDto> members;

    private String goal;

    private String content;

    private Boolean includeResultReport;

    private Boolean includeCode;

    private Boolean includeOutCome;

    private String includeOthers;

    private String writer;

}

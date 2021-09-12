package com.submit.toyproject.rms_backend_springboot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {

    @NotNull
    private String goal;

    @NotNull
    private String content;

    @NotNull
    private Boolean includeResultReport;

    @NotNull
    private Boolean includeCode;

    @NotNull
    private Boolean includeOutCome;

    private String includeOthers;

    @Size(min = 7, max = 7)
    @NotNull
    private String plannedStartDate;

    @Size(min = 7, max = 7)
    @NotNull
    private String plannedEndDate;

}

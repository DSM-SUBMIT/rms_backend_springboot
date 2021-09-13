package com.submit.toyproject.rms_backend_springboot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {

    @Size(max = 15000)
    @NotNull
    private String content;

    private String videoUrl;

}

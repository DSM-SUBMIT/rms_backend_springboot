package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NumberRequest {

    @ApiModelProperty(value = "학년", example = "2")
    private int grade;

    @ApiModelProperty(value = "반",example = "2")
    private int cls;

    @ApiModelProperty(value = "번호", example = "3")
    private int number;
}

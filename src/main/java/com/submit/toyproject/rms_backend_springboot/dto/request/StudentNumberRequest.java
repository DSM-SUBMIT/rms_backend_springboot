package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentNumberRequest {

    @ApiModelProperty(value = "1101 <= x <= 3430", example = "1111")
    @Min(1101)
    @Max(3430)
    Integer studentNumber;
}

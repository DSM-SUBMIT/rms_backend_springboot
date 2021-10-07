package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentNumberRequest {

    @ApiModelProperty(value = "1101 <= x <= 3430", example = "1111")
    Integer studentNumber;
}

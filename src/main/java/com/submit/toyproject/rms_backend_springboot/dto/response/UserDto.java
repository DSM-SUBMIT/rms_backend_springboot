package com.submit.toyproject.rms_backend_springboot.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(example = "<유저 id>")
    private Integer id;

    @ApiModelProperty(example = "<이름>")
    private String name;

    @ApiModelProperty(example = "<이메일>")
    private String email;

}

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
public class ProjectMemberDto {

    @ApiModelProperty(value = "멤버 이름", example = "곽도현")
    private String name;

    @ApiModelProperty(value = "멤버 이메일", example = "200000gdh@dsm.hs.kr")
    private String email;

    @ApiModelProperty(value = "멤버의 역할", example = "PM, Design, Server")
    private String role;

}
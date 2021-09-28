package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
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

    @ApiModelProperty(value = "멤버 이메일", example = "201403gdh@dsm.hs.kr")
    private String email;

    @ApiModelProperty(value = "멤버의 역할", example = "PM, Design, Server")
    private String role;

    public static ProjectMemberDto of(Member member) {
        return ProjectMemberDto.builder()
                .email(member.getUser().getEmail())
                .role(member.getRole())
                .build();
    }
}
package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDto {

    @Schema(description = "멤버의 유저 아이디", example = "1")
    private Integer id;

    @Schema(description = "멤버 이메일", example = "201403gdh@dsm.hs.kr")
    private String email;

    @Schema(description = "멤버 이름", example = "김해교")
    private String name;

    @Schema(description = "멤버의 역할", example = "PM, Design, Server")
    private String role;

    public static ProjectMemberDto of(Member member) {
        return ProjectMemberDto.builder()
                .id(member.getUser().getId())
                .email(member.getUser().getEmail())
                .name(member.getUser().getName())
                .role(member.getRole())
                .build();
    }
}
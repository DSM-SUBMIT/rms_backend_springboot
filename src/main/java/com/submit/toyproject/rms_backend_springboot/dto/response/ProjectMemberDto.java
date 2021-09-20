package com.submit.toyproject.rms_backend_springboot.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDto {
    private String name;
    private String email;
    private String role;
}
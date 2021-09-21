package com.submit.toyproject.rms_backend_springboot.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {

    private String name;

    private String email;

    private List<ProjectListElementDto> projectList;

}

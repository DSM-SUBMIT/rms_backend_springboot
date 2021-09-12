package com.submit.toyproject.rms_backend_springboot.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {

    private String name;
    private String email;
    private List<MyPageProjectResponse> projectList;
}

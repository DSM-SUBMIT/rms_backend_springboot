package com.submit.toyproject.rms_backend_springboot.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListElementDto {

    private Integer id;
    private String projectName;
    private String teamName;
    private String projectType;
    private List<String> fieldList;
}

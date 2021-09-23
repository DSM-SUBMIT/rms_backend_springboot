package com.submit.toyproject.rms_backend_springboot.domain.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectType {
    CLUB("동아리 프로젝트"),
    TEAM("팀 프로젝트"),
    PERS("개인 프로젝트"),
    PRO1("프로젝트 실무 1"),
    PRO2("프로젝트 실무 2"),
    SOFE("소프트웨어 공학");

    private final String division;
}

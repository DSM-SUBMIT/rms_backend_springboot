package com.submit.toyproject.rms_backend_springboot.domain.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectType {
    CLUB("동아리프로젝트"),
    TEAM("팀프로젝트"),
    PERS("개인프로젝트"),
    PRO1("프로젝트실무1"),
    PRO2("프로젝트실무2"),
    SOFE("소프트웨어공학");

    private final String division;
}

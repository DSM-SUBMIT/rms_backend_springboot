package com.submit.toyproject.rms_backend_springboot.domain.field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldEnum {
    WEB("웹"),
    APP("앱"),
    GAME("게임"),
    EMBEDDED("임베디드"),
    AI_BIGDATA("인공지능/빅데이터"),
    SECURITY("보안");

    private final String field;
}

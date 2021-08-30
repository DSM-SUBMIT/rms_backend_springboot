package com.submit.toyproject.rms_backend_springboot.domain.report.language;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Language {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String language;

}

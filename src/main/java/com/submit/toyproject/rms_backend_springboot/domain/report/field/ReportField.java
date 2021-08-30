package com.submit.toyproject.rms_backend_springboot.domain.report.field;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "field")
public class ReportField {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Field field;

}

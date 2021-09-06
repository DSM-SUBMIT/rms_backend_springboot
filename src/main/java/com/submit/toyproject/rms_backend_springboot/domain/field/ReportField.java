package com.submit.toyproject.rms_backend_springboot.domain.field;

import com.submit.toyproject.rms_backend_springboot.domain.report.Report;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Builder
    public ReportField(Field field, Report report) {
        this.field = field;
        this.report = report;
    }

}

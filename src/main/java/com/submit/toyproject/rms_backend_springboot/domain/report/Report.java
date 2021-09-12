package com.submit.toyproject.rms_backend_springboot.domain.report;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Report {

    @Id
    private Integer projectId;

    @Size(max = 15000)
    @NotNull
    private String content;

    private String videoUrl;

    private String pdfUrl;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

}

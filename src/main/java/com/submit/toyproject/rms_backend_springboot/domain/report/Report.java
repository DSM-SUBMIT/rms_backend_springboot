package com.submit.toyproject.rms_backend_springboot.domain.report;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;
import lombok.AccessLevel;
import lombok.Builder;
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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Report(Project project) {
        this.project = project;
    }

    public void save(ReportRequest request) {
        this.content = request.getContent();
        this.videoUrl = request.getVideoUrl();
    }

}

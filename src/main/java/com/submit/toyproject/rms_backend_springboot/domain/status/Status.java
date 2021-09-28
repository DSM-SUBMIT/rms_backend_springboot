package com.submit.toyproject.rms_backend_springboot.domain.status;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Status {

    @Id
    private Integer projectId;

    @Column(columnDefinition = "tinyint(1)")
    private Boolean isPlanAccepted;
    
    @NotNull
    @Column(columnDefinition = "tinyint(1) default false")
    private Boolean isPlanSubmitted;

    @Column(columnDefinition = "tinyint(1)")
    private Boolean isReportAccepted;

    @NotNull
    @Column(columnDefinition = "tinyint(1) default false")
    private Boolean isReportSubmitted;

    private LocalDateTime planSubmittedAt;

    private LocalDateTime reportSubmittedAt;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Status(Project project) {
        this.project = project;
        this.isPlanSubmitted = false;
        this.isReportSubmitted = false;
    }

    public Status planSubmit() {
        this.isPlanSubmitted = true;
        this.planSubmittedAt = LocalDateTime.now();
        this.isPlanAccepted = null;

        return this;
    }

    public Status reportSubmit() {
        this.isReportSubmitted = true;
        this.reportSubmittedAt = LocalDateTime.now();
        this.isReportAccepted = null;

        return this;
    }

}

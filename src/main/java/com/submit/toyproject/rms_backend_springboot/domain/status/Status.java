package com.submit.toyproject.rms_backend_springboot.domain.status;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Status {

    @Id
    private Integer reportId;

    @Column(columnDefinition = "bit(1)")
    private Boolean isPlanAccepted;

    @Column(columnDefinition = "bit(1)")
    private Boolean isPlanSubmitted;

    @Column(columnDefinition = "bit(1)")
    private Boolean isReportAccepted;

    @Column(columnDefinition = "bit(1)")
    private Boolean isReportSubmitted;

    private LocalDateTime planSubmittedAt;

    private LocalDateTime reportSubmittedAt;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Builder
    public Status(Project project) {
        this.project = project;
    }

    public void planSubmit() {
        this.isPlanSubmitted = true;
        this.planSubmittedAt = LocalDateTime.now();
    }

    public void reportSubmit() {
        this.isReportSubmitted = true;
        this.reportSubmittedAt = LocalDateTime.now();
    }

}

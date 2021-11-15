package com.submit.toyproject.rms_backend_springboot.domain.plan;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Plan {

    @Id
    private Integer projectId;

    @Size(max = 4000)
    private String goal;

    @Size(max = 10000)
    private String content;

    @Column(nullable = false)
    private boolean includeResultReport;

    @Column(nullable = false)
    private boolean includeCode;

    @Column(nullable = false)
    private boolean includeOutcome;

    @Size(max = 30)
    private String includeOthers;

    @Column(columnDefinition = "char(7)")
    private String startDate;

    @Column(columnDefinition = "char(7)")
    private String endDate;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Plan(Project project) {
        this.project = project;
        this.includeCode = false;
        this.includeOutcome = false;
        this.includeResultReport = false;
    }

    public Plan save(PlanRequest request) {
        this.goal = request.getGoal();
        this.content = request.getContent();
        this.includeResultReport = request.getIncludeResultReport();
        this.includeCode = request.getIncludeCode();
        this.includeOutcome = request.getIncludeOutcome();
        this.includeOthers = request.getIncludeOthers();
        this.startDate = request.getPlannedStartDate();
        this.endDate = request.getPlannedEndDate();

        return this;
    }

}

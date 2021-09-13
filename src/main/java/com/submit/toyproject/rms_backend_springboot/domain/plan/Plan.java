package com.submit.toyproject.rms_backend_springboot.domain.plan;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
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
public class Plan {

    @Id
    private Integer projectId;

    @NotNull
    @Size(max = 4000)
    private String goal;

    @NotNull
    @Size(max = 10000)
    private String content;

    @Size(max = 256)
    private String pdfUrl;

    private Boolean includeResultReport;

    private Boolean includeCode;

    private Boolean includeOutCome;

    @Size(max = 30)
    private String includeOthers;

    @NotNull
    @Column(columnDefinition = "char(7)")
    private String startDate;

    @NotNull
    @Column(columnDefinition = "char(7)")
    private String endDate;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Builder
    public Plan(String goal, String content, String pdfUrl, Project project, Boolean includeResultReport, Boolean includeCode, Boolean includeOutCome, String includeOthers) {
        this.goal = goal;
        this.content = content;
        this.pdfUrl = pdfUrl;
        this.project = project;
        this.includeResultReport = includeResultReport;
        this.includeCode = includeCode;
        this.includeOutCome = includeOutCome;
        this.includeOthers = includeOthers;
    }

}

package com.submit.toyproject.rms_backend_springboot.domain.project;

import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
import com.submit.toyproject.rms_backend_springboot.domain.plan.Plan;
import com.submit.toyproject.rms_backend_springboot.domain.report.Report;
import com.submit.toyproject.rms_backend_springboot.domain.status.Status;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @NotNull
    private String projectName;

    @Size(max = 20)
    @NotNull
    private String teamName;

    @Size(max = 100)
    @NotNull
    private String techStacks;

    @NotNull
    @Column(columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @Size(max = 10)
    @NotNull
    private String teacher;

    @Size(max = 500)
    private String githubUrl;

    @Size(max = 256)
    private String serviceUrl;

    @Size(max = 256)
    private String docsUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Status status;

    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Report report;

    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Plan plan;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Member> members;

    @Builder
    public Project(String projectName, String teamName, String techStacks, ProjectType projectType, String githubUrl, String serviceUrl, String docsUrl, String teacher) {
        this.projectName = projectName;
        this.teamName = teamName;
        this.techStacks = techStacks;
        this.projectType = projectType;
        this.teacher = teacher;
        this.githubUrl = githubUrl;
        this.serviceUrl = serviceUrl;
        this.docsUrl = docsUrl;
    }

}
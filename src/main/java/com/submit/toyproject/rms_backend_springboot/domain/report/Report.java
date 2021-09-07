package com.submit.toyproject.rms_backend_springboot.domain.report;

import com.submit.toyproject.rms_backend_springboot.domain.field.ReportField;
import com.submit.toyproject.rms_backend_springboot.domain.language.ReportLanguage;
import com.submit.toyproject.rms_backend_springboot.domain.team.ReportTeam;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @NotNull
    private String projectName;

    @Size(max = 20000)
    @NotNull
    private String content;

    @Size(max = 6)
    private String teacher;

    private String githubUrl;

    private String videoUrl;

    private String pdfUrl;

    @NotNull
    @Column(columnDefinition = "char(7)")
    private String startDate;

    @NotNull
    @Column(columnDefinition = "char(7)")
    private String endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Division division;

    @NotNull
    private Boolean isPublic;

    @Embedded
    private Status status;

    @OneToMany(mappedBy = "language", cascade = CascadeType.REMOVE)
    private List<ReportLanguage> reportLanguages;

    @OneToMany(mappedBy = "report", cascade = CascadeType.REMOVE)
    private List<ReportField> reportFields;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private ReportTeam reportTeam;

    @Builder
    public Report(String projectName, String teacher, String githubUrl, String videoUrl, String pdfUrl, Division division, Boolean isPublic, String content, User user, String startDate, String endDate) {
        this.projectName = projectName;
        this.teacher = teacher;
        this.githubUrl = githubUrl;
        this.videoUrl = videoUrl;
        this.pdfUrl = pdfUrl;
        this.division = division;
        this.isPublic = isPublic;
        this.content = content;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}

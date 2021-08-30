package com.submit.toyproject.rms_backend_springboot.domain.report;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @NotNull
    private String projectName;

    @Size(max = 6)
    private String teacher;

    private String githubUrl;

    private String videoUrl;

    private String pdfUrl;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private Boolean isTeam;

    @NotNull
    private Boolean isPublic;

    @Embedded
    private Status status;

}

package com.submit.toyproject.rms_backend_springboot.domain.bookmark;

import com.submit.toyproject.rms_backend_springboot.domain.report.Report;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Bookmark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Bookmark(Report report, User user) {
        this.report = report;
        this.user = user;
    }

}

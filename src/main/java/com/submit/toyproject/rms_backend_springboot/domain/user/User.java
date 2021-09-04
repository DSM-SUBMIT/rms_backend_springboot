package com.submit.toyproject.rms_backend_springboot.domain.user;

import com.submit.toyproject.rms_backend_springboot.domain.bookmark.Bookmark;
import com.submit.toyproject.rms_backend_springboot.domain.report.Report;
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
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(unique = true)
    private String email;

    @Size(max = 6)
    @NotNull
    private String name;

    @Size(max = 500)
    private String selfIntroduce;

    private String githubUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Report> reports;

    @Builder
    public User(String email, String name, String githubUrl, String selfIntroduce) {
        this.email = email;
        this.name = name;
        this.githubUrl = githubUrl;
        this.selfIntroduce = selfIntroduce;
    }

}

package com.submit.toyproject.rms_backend_springboot.domain.member;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(MemberId.class)
@Entity
public class Member {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Id
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @Id
    private Project project;

    @Size(max = 100)
    @NotNull
    private String role;

    @Builder
    public Member(User user, Project project, String role) {
        this.user = user;
        this.project = project;
        this.role = role;
    }

}

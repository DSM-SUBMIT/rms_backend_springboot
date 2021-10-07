package com.submit.toyproject.rms_backend_springboot.domain.user;

import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.dto.request.NumberRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
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

    private int grade;

    private int cls;

    private int number;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Project> projects;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Member> members;

    @Builder
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void saveNumber(NumberRequest request) {
        this.grade = request.getGrade();
        this.cls = request.getCls();
        this.number = request.getNumber();
    }

}

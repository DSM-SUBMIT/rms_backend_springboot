package com.submit.toyproject.rms_backend_springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(columnDefinition = "char(60)")
    private String password;

    @Size(max = 6)
    @NotNull
    private String name;

    @Size(max = 500)
    private String selfIntroduce;

    private String githubUrl;

    @Builder
    public User(String email, String password, String name, String githubUrl, String selfIntroduce) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.githubUrl = githubUrl;
        this.selfIntroduce = selfIntroduce;
    }

}

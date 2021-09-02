package com.submit.toyproject.rms_backend_springboot.domain.admin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Admin {

    @Size(max = 15)
    @Id
    private String id;

    @NotNull
    @Column(columnDefinition = "char(60)")
    private String password;

}

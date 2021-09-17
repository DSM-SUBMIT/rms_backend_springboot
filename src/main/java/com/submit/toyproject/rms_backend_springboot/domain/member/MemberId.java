package com.submit.toyproject.rms_backend_springboot.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberId implements Serializable {

    private Integer user;
    private Integer project;
}

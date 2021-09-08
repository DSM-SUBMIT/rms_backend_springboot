package com.submit.toyproject.rms_backend_springboot.domain.member;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberId implements Serializable {

    private User user;

    private Project project;

}

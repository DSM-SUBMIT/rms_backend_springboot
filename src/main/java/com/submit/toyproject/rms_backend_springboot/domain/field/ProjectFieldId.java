package com.submit.toyproject.rms_backend_springboot.domain.field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFieldId implements Serializable {

    @Id
    private Integer field;

    @Id
    private Integer project;

}

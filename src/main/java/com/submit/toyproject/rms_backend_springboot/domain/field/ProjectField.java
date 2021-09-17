package com.submit.toyproject.rms_backend_springboot.domain.field;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ProjectFieldId.class)
@Entity
public class ProjectField {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    @Id
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @Id
    private Project project;

    @Builder
    public ProjectField(Field field, Project project) {
        this.field = field;
        this.project = project;
    }

}

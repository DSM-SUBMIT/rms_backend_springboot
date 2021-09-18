package com.submit.toyproject.rms_backend_springboot.domain.field;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Field {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FieldEnum field;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ProjectField> projectFields;

}

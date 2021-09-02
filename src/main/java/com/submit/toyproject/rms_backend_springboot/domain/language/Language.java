package com.submit.toyproject.rms_backend_springboot.domain.language;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Language {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String language;

    @OneToMany(mappedBy = "language", cascade = CascadeType.REMOVE)
    private List<ReportLanguage> reportLanguages;

}

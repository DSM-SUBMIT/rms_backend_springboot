package com.submit.toyproject.rms_backend_springboot.domain.bookmark;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Bookmark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}

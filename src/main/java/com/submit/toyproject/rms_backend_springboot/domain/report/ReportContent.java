package com.submit.toyproject.rms_backend_springboot.domain.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ReportContent {

    @MongoId
    private Integer reportId;

    private String content;

}

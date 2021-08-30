package com.submit.toyproject.rms_backend_springboot.domain.report;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportContentRepository extends MongoRepository<ReportContent, Integer> {
}

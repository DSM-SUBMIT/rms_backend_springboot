package com.submit.toyproject.rms_backend_springboot.service.report;

import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;

public interface ReportService {
    void saveReport(Integer projectId, ReportRequest request);
    void updateReport(Integer id, ReportRequest request);
}
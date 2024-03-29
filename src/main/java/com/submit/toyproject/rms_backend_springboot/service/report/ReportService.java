package com.submit.toyproject.rms_backend_springboot.service.report;

import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.ReportResponse;

public interface ReportService {
    void saveReport(Integer projectId, ReportRequest request);
    ReportResponse getReportInfo(Integer id);
    void submitReport(Integer id);
}
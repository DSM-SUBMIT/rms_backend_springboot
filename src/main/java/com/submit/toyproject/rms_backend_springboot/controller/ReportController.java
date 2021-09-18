package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.ReportResponse;
import com.submit.toyproject.rms_backend_springboot.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportController {

    private final ReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void saveReport(@PathVariable Integer id,
                           @RequestBody @Valid ReportRequest request) {
        reportService.saveReport(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateReport(@PathVariable Integer id,
                             @RequestBody @Valid ReportRequest request) {
        reportService.updateReport(id, request);
    }

    @GetMapping("/{id}")
    public ReportResponse getReportInfo(@PathVariable Integer id) {
        return reportService.getReportInfo(id);
    }

    @PostMapping("/submit/{id}")
    public void submitReport(@PathVariable Integer id) {
        reportService.submitReport(id);
    }

}

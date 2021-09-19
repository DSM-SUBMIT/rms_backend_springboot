package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.ReportResponse;
import com.submit.toyproject.rms_backend_springboot.service.report.ReportService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportController {

    private final ReportService reportService;

    @ApiOperation(value = "보고서 저장", notes = "작성한 보고서를 임시저장하는 기능입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void saveReport(@PathVariable Integer id,
                           @RequestBody @Valid ReportRequest request) {
        reportService.saveReport(id, request);
    }

    @ApiOperation(value = "보고서 수정", notes = "보고서 제출 전 또는 미승인되어 반환되었을 때 보고서를 수정할 수 있습니다.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateReport(@PathVariable Integer id,
                             @RequestBody @Valid ReportRequest request) {
        reportService.updateReport(id, request);
    }

    @ApiOperation(value = "보고서 보기", notes = "승인된 보고서가 아니라면 멤버만 확인할 수 있습니다.")
    @GetMapping("/{id}")
    public ReportResponse getReportInfo(@PathVariable Integer id) {
        return reportService.getReportInfo(id);
    }

    @ApiOperation(value = "보고서 제출", notes = "저장된 보고서를 제출하는 기능입니다. 작성 중 제출할 때는 저장 api를 호출한 이후 제출 api를 호출해야 합니다.")
    @PostMapping("/submit/{id}")
    public void submitReport(@PathVariable Integer id) {
        reportService.submitReport(id);
    }

}

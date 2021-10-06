package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.ReportResponse;
import com.submit.toyproject.rms_backend_springboot.service.report.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "보고서")
@RequiredArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "보고서 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "보고서 저장/수정이 성공적으로 이루어짐"),
            @ApiResponse(responseCode = "400", description = "계획서가 승인되지 않음/보고서가 이미 제출됨"),
            @ApiResponse(responseCode = "401", description = "프로젝트 생성자가 아님"),
            @ApiResponse(responseCode = "404", description = "프로젝트/보고서를 찾을 수 없음")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void saveReport(@PathVariable Integer id,
                           @RequestBody @Valid ReportRequest request) {
        reportService.saveReport(id, request);
    }

    @Operation(summary = "보고서 제출", description = "보고서 제출(작성 중 제출할 때는 저장 api를 호출한 이후 제출 api를 호출해야 함)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "계획서가 성공적으로 제출됨"),
            @ApiResponse(responseCode = "400", description = "계획서가 승인되지 않음/보고서가 이미 제출됨/메일 전송 오류"),
            @ApiResponse(responseCode = "401", description = "프로젝트 생성자가 아님"),
            @ApiResponse(responseCode = "404", description = "보고서를 찾을 수 없음")
    })
    @PostMapping("/submit/{id}")
    public void submitReport(@PathVariable Integer id) {
        reportService.submitReport(id);
    }

    @Operation(summary = "보고서 보기", description = "승인된 보고서가 아니라면 멤버만 확인할 수 있음")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "보고서를 성공적으로 가져옴"),
            @ApiResponse(responseCode = "403", description = "보고서 보기에 대한 권한이 없음"),
            @ApiResponse(responseCode = "404", description = "보고서를 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ReportResponse getReportInfo(@PathVariable Integer id) {
        return reportService.getReportInfo(id);
    }

}

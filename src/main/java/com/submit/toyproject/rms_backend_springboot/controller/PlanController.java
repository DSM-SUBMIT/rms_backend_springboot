package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.PlanResponse;
import com.submit.toyproject.rms_backend_springboot.service.plan.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"계획서"})
@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
public class PlanController {

    private final PlanService planService;

    @ApiOperation(value = "계획서 저장")
    @ApiResponses({
            @ApiResponse(code = 201, message = "계획서 저장/수정이 성공적으로 이루어짐"),
            @ApiResponse(code = 400, message = "계획서가 이미 제출됨"),
            @ApiResponse(code = 401, message = "프로젝트 생성자가 아님"),
            @ApiResponse(code = 404, message = "프로젝트/계획서를 찾을 수 없음")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{projectId}")
    public void savePlan(@PathVariable Integer projectId,
                         @RequestBody @Valid PlanRequest request) {
        planService.savePlan(projectId, request);
    }

    @ApiOperation(value = "계획서 제출", notes = "계획서 제출 (작성 중 제출할 때는 저장 api를 호출한 이후 제출 api를 호출해야 함)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "계획서가 성공적으로 제출됨"),
            @ApiResponse(code = 400, message = "계획서가 이미 제출됨"),
            @ApiResponse(code = 401, message = "프로젝트 생성자가 아님"),
            @ApiResponse(code = 404, message = "계획서를 찾을 수 없음")
    })
    @PostMapping("/submit/{projectId}")
    public void submitPlan(@PathVariable Integer projectId) {
        planService.submitPlan(projectId);
    }

    @ApiOperation(value = "계획서 보기", notes = "승인된 계획서가 아니라면 멤버만 확인할 수 있음")
    @ApiResponses({
            @ApiResponse(code = 200, message = "계획서를 성공적으로 가져옴"),
            @ApiResponse(code = 403, message = "계획서 보기에 대한 권한이 없음"),
            @ApiResponse(code = 404, message = "계획서를 찾을 수 없음")
    })
    @GetMapping("/{projectId}")
    public PlanResponse getPlanInfo(@PathVariable Integer projectId) {
        return planService.getPlanInfo(projectId);
    }

}

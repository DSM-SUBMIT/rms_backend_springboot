package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.PlanResponse;
import com.submit.toyproject.rms_backend_springboot.service.plan.PlanService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
public class PlanController {

    private final PlanService planService;

    @ApiOperation(value = "계획서 저장", notes = "작성한 계획서를 임시저장하는 기능입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void savePlan(@PathVariable Integer id,
                         @RequestBody @Valid PlanRequest request) {
        planService.savePlan(id, request);
    }

    @ApiOperation(value = "계획서 수정", notes = "계획서 제출 전 또는 미승인되어 반환되었을 때 계획서를 수정할 수 있습니다.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updatePlan(@PathVariable Integer id,
                           @RequestBody @Valid PlanRequest request) {
        planService.updatePlan(id, request);
    }

    @ApiOperation(value = "계획서 제출", notes = "저장된 계획서를 제출하는 기능입니다. 작성 중 제출할 때는 저장 api를 호출한 이후 제출 api를 호출해야 합니다.")
    @PostMapping("/submit/{id}")
    public void submitPlan(@PathVariable Integer id) {
        planService.submitPlan(id);
    }

    @ApiOperation(value = "계획서 보기", notes = "승인된 계획서가 아니라면 멤버만 확인할 수 있습니다.")
    @GetMapping("/{id}")
    public PlanResponse getPlanInfo(@PathVariable Integer id) {
        return planService.getPlanInfo(id);
    }

}

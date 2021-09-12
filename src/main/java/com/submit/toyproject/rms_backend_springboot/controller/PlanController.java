package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import com.submit.toyproject.rms_backend_springboot.service.plan.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
public class PlanController {

    private final PlanService planService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void savePlan(@PathVariable Integer id,
                         @RequestBody @Valid PlanRequest request) {
        planService.savePlan(id, request);
    }

}

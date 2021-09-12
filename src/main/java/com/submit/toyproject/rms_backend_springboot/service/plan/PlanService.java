package com.submit.toyproject.rms_backend_springboot.service.plan;

import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.PlanResponse;

public interface PlanService {
    void savePlan(Integer projectId, PlanRequest request);
    void updatePlan(Integer id, PlanRequest request);
    void savePlanService(Integer id);
    PlanResponse getPlanInfo(Integer id);
}

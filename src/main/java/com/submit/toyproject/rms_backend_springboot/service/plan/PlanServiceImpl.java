package com.submit.toyproject.rms_backend_springboot.service.plan;

import com.submit.toyproject.rms_backend_springboot.domain.plan.Plan;
import com.submit.toyproject.rms_backend_springboot.domain.plan.PlanRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.status.StatusRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.MemberDto;
import com.submit.toyproject.rms_backend_springboot.dto.response.PlanResponse;
import com.submit.toyproject.rms_backend_springboot.exception.ProjectNotFoundException;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotHavePermissionException;
import com.submit.toyproject.rms_backend_springboot.exception.handler.PlanNotFoundException;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    private final ProjectRepository projectRepository;
    private final PlanRepository planRepository;
    private final StatusRepository statusRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void savePlan(Integer projectId, PlanRequest request) {
        User user = authenticationFacade.certifiedUser();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);

        if (!project.getUser().equals(user)) {
            throw new UserNotHavePermissionException();
        }

        planRepository.save(
                Plan.builder()
                        .goal(request.getGoal())
                        .content(request.getGoal())
                        .project(project)
                        .includeResultReport(request.getIncludeResultReport())
                        .includeCode(request.getIncludeCode())
                        .includeOutCome(request.getIncludeOutCome())
                        .includeOthers(request.getIncludeOthers())
                        .plannedStartDate(request.getPlannedStartDate())
                        .plannedEndDate(request.getPlannedEndDate())
                        .build()
        );
    }

    @Override
    public void updatePlan(Integer id, PlanRequest request) {
        User user = authenticationFacade.certifiedUser();

        Plan plan = getPlan(id);

        if (!plan.getProject().getUser().equals(user)) {
            throw new UserNotHavePermissionException();
        }

        planRepository.save(plan.update(request));
    }

    @Override
    public void savePlanService(Integer id) {
        User user = authenticationFacade.certifiedUser();

        Plan plan = getPlan(id);

        if (!plan.getProject().getUser().equals(user)) {
            throw new UserNotHavePermissionException();
        }

        statusRepository.save(plan.getProject().getStatus().planSubmit());
    }

    @Override
    public PlanResponse getPlanInfo(Integer id) {
        authenticationFacade.certifiedUser();;

        Plan plan = getPlan(id);

        return PlanResponse.builder()
                .projectName(plan.getProject().getProjectName())
                .writer(plan.getProject().getUser().getName())
                .plannedStartDate(plan.getStartDate())
                .plannedEndDate(plan.getEndDate())
                .goal(plan.getGoal())
                .content(plan.getContent())
                .includeResultReport(plan.getIncludeResultReport())
                .includeCode(plan.getIncludeCode())
                .includeOutCome(plan.getIncludeOutCome())
                .includeOthers(plan.getIncludeOthers())
                .members(
                        plan.getProject().getMembers().stream()
                        .map(member -> new MemberDto(member.getUser().getName(), member.getRole()))
                        .collect(Collectors.toList())
                )
                .build();
    }

    public Plan getPlan(Integer id) {
        return planRepository.findById(id)
                .orElseThrow(PlanNotFoundException::new);
    }

}

package com.submit.toyproject.rms_backend_springboot.service.plan;

import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.plan.Plan;
import com.submit.toyproject.rms_backend_springboot.domain.plan.PlanRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.status.StatusRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.MemberDto;
import com.submit.toyproject.rms_backend_springboot.dto.response.PlanResponse;
import com.submit.toyproject.rms_backend_springboot.exception.*;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    private final ProjectRepository projectRepository;
    private final PlanRepository planRepository;
    private final StatusRepository statusRepository;
    private final MemberRepository memberRepository;

    private final AuthenticationFacade authenticationFacade;

    @Transactional
    @Override
    public void savePlan(Integer projectId, PlanRequest request) {
        User user = authenticationFacade.certifiedUser();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        isWorkPossible(project, user);
        Plan plan;

        if (!planRepository.existsById(projectId)) {
            plan = new Plan(project);
        } else {
            plan = getPlan(projectId);
        }
        plan.save(request);
        planRepository.save(plan);
    }

    @Transactional
    @Override
    public void submitPlan(Integer id) {
        User user = authenticationFacade.certifiedUser();
        Plan plan = getPlan(id);
        isWorkPossible(plan.getProject(), user);

        statusRepository.save(plan.getProject().getStatus().planSubmit());
    }

    @Transactional(readOnly = true)
    @Override
    public PlanResponse getPlanInfo(Integer id) {
        User user = authenticationFacade.certifiedUser();
        Plan plan = getPlan(id);
        Project project = plan.getProject();

        if ((project.getStatus().getIsPlanAccepted() == null || !project.getStatus().getIsPlanAccepted()) && !memberRepository.existsByProjectAndUser(project, user)) {
            throw new UserNotHavePermissionException();
        }

        if (plan.getGoal() == null) {
            throw new PlanNotSubmittedException();
        }

        return PlanResponse.builder()
                .projectName(project.getProjectName())
                .writer(project.getWriter().getName())
                .plannedStartDate(plan.getStartDate())
                .plannedEndDate(plan.getEndDate())
                .goal(plan.getGoal())
                .content(plan.getContent())
                .includeResultReport(plan.getIncludeResultReport())
                .includeCode(plan.getIncludeCode())
                .includeOutcome(plan.getIncludeOutcome())
                .isTeam(project.getMembers().size() > 2)
                .includeOthers(plan.getIncludeOthers())
                .members(
                        project.getMembers().stream()
                        .map(member -> new MemberDto(member.getUser().getName(), member.getRole()))
                        .collect(Collectors.toList())
                )
                .build();
    }

    private Plan getPlan(Integer id) {
        return planRepository.findById(id)
                .orElseThrow(PlanNotFoundException::new);
    }

    private void isWorkPossible(Project project, User user) {
        if (!project.getWriter().getEmail().equals(user.getEmail())) {
            throw new UserNotHavePermissionException();
        }
        if (project.getStatus().getIsPlanSubmitted()) {
            throw new PlanAlreadySubmittedException();
        }
    }

}

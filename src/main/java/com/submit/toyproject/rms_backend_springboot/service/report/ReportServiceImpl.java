package com.submit.toyproject.rms_backend_springboot.service.report;

import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.report.Report;
import com.submit.toyproject.rms_backend_springboot.domain.report.ReportRepository;
import com.submit.toyproject.rms_backend_springboot.domain.status.StatusRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.ReportResponse;
import com.submit.toyproject.rms_backend_springboot.exception.*;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ProjectRepository projectRepository;
    private final ReportRepository reportRepository;
    private final StatusRepository statusRepository;
    private final MemberRepository memberRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void saveReport(Integer projectId, ReportRequest request) {
        User user = authenticationFacade.certifiedUser();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        isAcceptable(project, user);

        reportRepository.save(
                Report.builder()
                        .project(project)
                        .content(request.getContent())
                        .videoUrl(request.getVideoUrl())
                        .build()
        );
    }

    @Override
    public void updateReport(Integer id, ReportRequest request) {
        User user = authenticationFacade.certifiedUser();
        Report report = getReport(id);
        isAcceptable(report.getProject(), user);
        isSubmitted(report.getProject());

        reportRepository.save(report.update(request));
    }

    @Override
    public ReportResponse getReportInfo(Integer id) {
        User user = authenticationFacade.certifiedUser();
        Report report = getReport(id);
        Project project = report.getProject();

        if (!project.getStatus().getIsPlanAccepted() && !memberRepository.existsByProjectAndUser(project, user)) {
            throw new UserNotHavePermissionException();
        }

        return ReportResponse.builder()
                .writer(project.getUser().getName())
                .projectType(project.getProjectType().toString())
                .videoUrl(report.getVideoUrl())
                .content(report.getContent())
                .projectName(project.getProjectName())
                .field(project.getProjectFields().stream()
                    .map(field -> field.getField().getField().toString())
                    .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void submitReport(Integer id) {
        User user = authenticationFacade.certifiedUser();
        Report report = getReport(id);
        isAcceptable(report.getProject(), user);
        isSubmitted(report.getProject());


        statusRepository.save(report.getProject().getStatus().reportSubmit());
    }

    private void isAcceptable(Project project, User user) {
        if (!project.getUser().equals(user)) {
            throw new UserNotHavePermissionException();
        }
    }

    private void isSubmitted(Project project) {
        if (project.getStatus().getIsPlanSubmitted()) {
            throw new ReportAlreadySubmittedException();
        }
    }

    private Report getReport(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(ReportNotFoundException::new);
    }

}

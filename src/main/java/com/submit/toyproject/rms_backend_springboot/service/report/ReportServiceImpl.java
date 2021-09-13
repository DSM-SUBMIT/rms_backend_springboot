package com.submit.toyproject.rms_backend_springboot.service.report;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.report.Report;
import com.submit.toyproject.rms_backend_springboot.domain.report.ReportRepository;
import com.submit.toyproject.rms_backend_springboot.domain.status.StatusRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.dto.request.ReportRequest;
import com.submit.toyproject.rms_backend_springboot.exception.ProjectNotFoundException;
import com.submit.toyproject.rms_backend_springboot.exception.ReportNotFoundException;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotHavePermissionException;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ProjectRepository projectRepository;
    private final ReportRepository reportRepository;
    private final StatusRepository statusRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void saveReport(Integer projectId, ReportRequest request) {
        User user = authenticationFacade.certifiedUser();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);

        if (!project.getUser().equals(user)) {
            throw new UserNotHavePermissionException();
        }

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

        if (!report.getProject().getUser().equals(user)) {
            throw new UserNotHavePermissionException();
        }

        reportRepository.save(report.update(request));
    }

    private Report getReport(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(ReportNotFoundException::new);
    }


}

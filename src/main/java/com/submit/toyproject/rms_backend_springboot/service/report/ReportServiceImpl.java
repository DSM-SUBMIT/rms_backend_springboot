package com.submit.toyproject.rms_backend_springboot.service.report;

import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ProjectRepository projectRepository;
    private final ReportRepository reportRepository;
    private final StatusRepository statusRepository;
    private final MemberRepository memberRepository;

    private final AuthenticationFacade authenticationFacade;

    private final JavaMailSender mailSender;

    @Transactional
    @Override
    public void saveReport(Integer projectId, ReportRequest request) {
        User user = authenticationFacade.certifiedUser();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        isWorkPossible(project, user);
        Report report = getReport(projectId);
        reportRepository.save(report.save(request));
    }

    @Async
    @Transactional
    @Override
    public void submitReport(Integer id) {
        User user = authenticationFacade.certifiedUser();
        Report report = getReport(id);
        isWorkPossible(report.getProject(), user);

        //sendMail(report.getProject());

        statusRepository.save(report.getProject().getStatus().reportSubmit());
    }

    @Transactional(readOnly = true)
    @Override
    public ReportResponse getReportInfo(Integer id) {
        User user = authenticationFacade.certifiedUser();
        Report report = getReport(id);
        Project project = report.getProject();

        if (project.getStatus().getIsReportAccepted() == null || !project.getStatus().getIsReportAccepted() && !memberRepository.existsByProjectAndUser(project, user)) {
            throw new UserNotHavePermissionException();
        }

        if(!project.getWriter().getEmail().equals(user.getEmail()) && report.getContent() == null) {
            throw new ReportNotSubmittedException();
        }

        return ReportResponse.builder()
                .writer(project.getWriter().getName())
                .writerStudentNumber(project.getWriter().getStudentNumber())
                .content(report.getContent())
                .projectName(report.getProject().getProjectName())
                .isTeam(project.getMembers().size() > 2)
                .build();
    }

    private void isWorkPossible(Project project, User user) {
        if (!project.getWriter().equals(user)) {
            throw new UserNotHavePermissionException();
        }
        if (project.getStatus().getIsPlanAccepted() == null || !project.getStatus().getIsPlanAccepted()) {
            throw new PlanNotAcceptedException();
        }
        if (project.getStatus().getIsReportSubmitted()) {
            throw new ReportAlreadySubmittedException();
        }
    }

    private Report getReport(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(ReportNotFoundException::new);
    }

    /*private void sendMail(Project project) {
        for (Member member : project.getMembers()) {
            try {
                final MimeMessagePreparator preparator = mimeMessage -> {
                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setFrom("dsmsubmit@gmail.com");
                    helper.setTo(member.getUser().getEmail());
                    helper.setSubject(project.getTeamName() + "의 프로젝트 보고서가 제출되었습니다.");
                    helper.setText(convertNotificationMemberAdd(project, member.getUser()), true);
                };
                mailSender.send(preparator);
            } catch (Exception e) {
                e.printStackTrace();
                throw new EmailSendFailException();
            }
        }
    }*/

    private String convertNotificationMemberAdd(Project project, User member) throws IOException {
        InputStream inputStream = new ClassPathResource("static/add_member_email.html").getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        bufferedReader.lines()
                .filter(Objects::nonNull)
                .forEach(stringBuilder::append);

        String body = stringBuilder.toString();
        body = body.replace("{projectName}", project.getProjectName());
        body = body.replace("{name}", member.getName());
        body = body.replace("{teamName}", project.getTeamName());

        return body;
    }

}

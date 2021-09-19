package com.submit.toyproject.rms_backend_springboot.service.project;

import com.submit.toyproject.rms_backend_springboot.domain.field.*;
import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectType;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectRequest;
import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectUrlsRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.MemberDto;
import com.submit.toyproject.rms_backend_springboot.dto.response.ProjectResponse;
import com.submit.toyproject.rms_backend_springboot.exception.*;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectFieldRepository projectFieldRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;

    private final JavaMailSender mailSender;

    private final AuthenticationFacade authenticationFacade;


    @Override
    @Transactional
    public Integer createProject(ProjectRequest projectRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(InvalidUserTokenException::new);

        Project project = Project.builder()
                .projectName(projectRequest.getProjectName())
                .teamName(projectRequest.getTeamName())
                .techStacks(projectRequest.getTechStacks())
                .projectType(ProjectType.valueOf(projectRequest.getProjectType()))
                .teacher(projectRequest.getTeacher())
                .writer(user)
                .build();

        projectRepository.save(project);
        addMember(project, projectRequest.getMemberList());
        addProjectField(project, projectRequest.getFieldList());

        return project.getId();
    }

    @Override
    public ProjectResponse getProject(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        List<String> fieldList = projectFieldRepository.findByProject(project).stream()
                .map(projectField -> projectField.getField().getField().toString())
                .collect(Collectors.toList());

        List<MemberDto> memberList = memberRepository.findByProject(project).stream()
                .map(member -> MemberDto.builder()
                        .name(member.getUser().getName())
                        .email(member.getUser().getEmail())
                        .role(member.getRole())
                        .build())
                .collect(Collectors.toList());

        return ProjectResponse.builder()
                .id(project.getId())
                .projectType(project.getProjectType().toString())
                .projectName(project.getProjectName())
                .fieldList(fieldList)
                .teamName(project.getTeamName())
                .memberList(memberList)
                .githubUrl(project.getGithubUrl())
                .serviceUrl(project.getServiceUrl())
                .docsUrl(project.getDocsUrl())
                .build();
    }

    @Override
    @Transactional
    public void updateProject(Integer id, ProjectRequest projectRequest) {

        Project project = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        if(!authenticationFacade.getUserEmail().equals(project.getWriter().getEmail())) {
            throw new PermissionDeniedException();
        }

        project.update(projectRequest);

        //수정 필요
        memberRepository.deleteAllByProject(project);
        addMember(project, projectRequest.getMemberList());
        projectFieldRepository.deleteAllByProject(project);
        addProjectField(project, projectRequest.getFieldList());
    }

    @Override
    @Transactional
    public void updateUrls(Integer id, ProjectUrlsRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        if(!authenticationFacade.getUserEmail().equals(project.getWriter().getEmail())) {
            throw new PermissionDeniedException();
        }

        project.updateUrls(request);
    }

    @Override
    @Transactional
    public void deleteProject(Integer id) {
        projectRepository.delete(projectRepository
                .findById(id).orElseThrow(UserNotFoundException::new));
    }

    private void addProjectField(Project project, List<String> fieldList) {

        for(String fieldReq : fieldList) {

            //Field 는 db에 미리 넣는게 낫지 않을까?
            FieldEnum fieldEnum = FieldEnum.valueOf(fieldReq);
            if(fieldRepository.findByField(fieldEnum).isEmpty()) {
                Field field = Field.builder()
                        .field(FieldEnum.valueOf(fieldReq))
                        .build();
                fieldRepository.save(field);
            }

            Field field = fieldRepository.findByField(fieldEnum)
                    .orElseThrow(FieldNotFoundException::new);

            projectFieldRepository.save(
                    ProjectField.builder()
                    .field(field)
                    .project(project)
                    .build()
            );
        }
    }

    private void addMember(Project project, List<Map<String, String>> memberList) {

        for(Map<String, String> memberMap : memberList) {

            User user = userRepository.findByEmail(memberMap.get("email"))
                    .orElseThrow(UserNotFoundException::new);

            Member member = Member.builder()
                    .project(project)
                    .user(user)
                    .role(memberMap.get("role"))
                    .build();

            memberRepository.save(member);

            sendMail(user.getEmail(), project.getTeamName());
        }
    }

    private void sendMail(String setTo, String teamName) {

        try {
            final MimeMessagePreparator preparator = mimeMessage -> {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom("dsmsubmit@gmail.com");
                helper.setTo(setTo);
                helper.setSubject("새로운 팀에 가입되었습니다.");
                helper.setText(convertNotificationMemberAdd(teamName), true);
            };

            mailSender.send(preparator);

        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailSendFailException();
        }
    }

    private String convertNotificationMemberAdd(String teamName) throws IOException {
        InputStream inputStream = new ClassPathResource("static/add_member_email.html").getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();

        bufferedReader.lines()
                .filter(Objects::nonNull)
                .forEach(stringBuilder::append);

        String body = stringBuilder.toString();

        return body.replace("{{server_url}}", "http://localhost:8081  " + teamName);
    }
}

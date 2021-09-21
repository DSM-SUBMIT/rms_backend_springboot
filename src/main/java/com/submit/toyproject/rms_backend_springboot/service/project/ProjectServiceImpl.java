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
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedProjectDetailResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.MyPageProjectDetailResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.ProjectMemberDto;
import com.submit.toyproject.rms_backend_springboot.exception.*;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectFieldRepository projectFieldRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;

    private final AuthenticationFacade authenticationFacade;


    @Override
    @Transactional
    public Integer createProject(ProjectRequest projectRequest) {
        User user = authenticationFacade.certifiedUser();

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
    @Transactional
    public MyPageProjectDetailResponse getMyProject(Integer id) {
        Project project = getProject(id);

        checkPermission(project);

        return MyPageProjectDetailResponse.builder()
                .id(project.getId())
                .projectType(project.getProjectType().getDivision())
                .projectName(project.getProjectName())
                .fieldList(getProjectField(project))
                .teamName(project.getTeamName())
                .isPlanSubmitted(project.getStatus().getIsPlanSubmitted())
                .isPlanAccepted(project.getStatus().getIsPlanAccepted())
                .isReportSubmitted(project.getStatus().getIsReportSubmitted())
                .isReportAccepted(project.getStatus().getIsReportAccepted())
                .memberList(getMemberList(project))
                .githubUrl(project.getGithubUrl())
                .serviceUrl(project.getServiceUrl())
                .docsUrl(project.getDocsUrl())
                .build();
    }

    @Override
    @Transactional
    public MainFeedProjectDetailResponse getProjectDetail(Integer id) {
        Project project = getProject(id);

        return MainFeedProjectDetailResponse.builder()
                .id(project.getId())
                .projectType(project.getProjectType().toString())
                .projectName(project.getProjectName())
                .fieldList(getProjectField(project))
                .teamName(project.getTeamName())
                .memberList(getMemberList(project))
                .githubUrl(project.getGithubUrl())
                .serviceUrl(project.getServiceUrl())
                .docsUrl(project.getDocsUrl())
                .build();
    }

    @Override
    @Transactional
    public void updateProject(Integer id, ProjectRequest projectRequest) {
        Project project = getProject(id);

        checkPermission(project);

        project.update(projectRequest);

        memberRepository.deleteAllByProject(project);
        addMember(project, projectRequest.getMemberList());
        projectFieldRepository.deleteAllByProject(project);
        addProjectField(project, projectRequest.getFieldList());
    }

    @Override
    @Transactional
    public void updateUrls(Integer id, ProjectUrlsRequest request) {
        Project project = getProject(id);

        checkPermission(project);

        project.updateUrls(request);
    }

    @Override
    @Transactional
    public void deleteProject(Integer id) {
        checkPermission(getProject(id));

        projectRepository.delete(projectRepository
                .findById(id).orElseThrow(ProjectNotFoundException::new));
    }

    private void addProjectField(Project project, List<String> fieldList) {
        for(String fieldReq : fieldList) {
            Field field = fieldRepository.findByField(FieldEnum.valueOf(fieldReq))
                    .orElseThrow(FieldNotFoundException::new);

            projectFieldRepository.save(
                    ProjectField.builder()
                    .field(field)
                    .project(project)
                    .build()
            );
        }
    }

    private List<String> getProjectField(Project project) {
        return projectFieldRepository.findByProject(project).stream()
                .map(projectField -> projectField.getField().getField().toString())
                .collect(Collectors.toList());
    }

    private List<ProjectMemberDto> getMemberList(Project project) {
        return memberRepository.findByProject(project).stream()
                .map(member -> ProjectMemberDto.builder()
                        .name(member.getUser().getName())
                        .email(member.getUser().getEmail())
                        .role(member.getRole())
                        .build())
                .collect(Collectors.toList());
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
        }
    }

    private Project getProject(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);
    }

    private void checkPermission(Project project) {
        if(!authenticationFacade.getUserEmail().equals(project.getWriter().getEmail())) {
            throw new PermissionDeniedException();
        }
    }

}

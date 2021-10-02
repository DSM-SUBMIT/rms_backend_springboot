package com.submit.toyproject.rms_backend_springboot.service.project;

import com.submit.toyproject.rms_backend_springboot.domain.field.*;
import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.status.Status;
import com.submit.toyproject.rms_backend_springboot.domain.status.StatusRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectFieldRepository projectFieldRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final StatusRepository statusRepository;

    private final AuthenticationFacade authenticationFacade;


    @Override
    @Transactional
    public Integer createProject(ProjectRequest request) {
        User user = authenticationFacade.certifiedUser();
        Project project = Project.of(request, user);

        statusRepository.save(new Status(project));
        projectRepository.save(project);

        addMember(project, request.getMemberList());
        addProjectField(project, request.getFieldList());

        return project.getId();
    }

    @Override
    @Transactional
    public MyPageProjectDetailResponse getMyProject(Integer id) {
        Project project = getProject(id);
        checkPermission(project);

        return MyPageProjectDetailResponse.of(project, getFieldEnumList(project), getProjectMemberList(project));
    }

    @Override
    @Transactional
    public MainFeedProjectDetailResponse getProjectDetail(Integer id) {
        Project project = getProject(id);

        return MainFeedProjectDetailResponse.of(project, getFieldEnumList(project), getProjectMemberList(project));
    }

    @Override
    @Transactional
    public void updateProject(Integer id, ProjectRequest request) {
        Project project = getProject(id);
        checkPermission(project);
        project.update(request);

        memberRepository.deleteAllByProject(project);
        addMember(project, request.getMemberList());

        projectFieldRepository.deleteAllByProject(project);
        addProjectField(project, request.getFieldList());
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
        projectRepository.delete(getProject(id));
    }

    private void addProjectField(Project project, List<FieldEnum> fieldList) {
        fieldList.forEach(fieldEnum -> projectFieldRepository
                .save(new ProjectField(getField(fieldEnum), project)));
    }

    private void addMember(Project project, List<ProjectMemberDto> memberList) {
        memberList.forEach(member -> memberRepository
                .save(new Member(getUserByMember(member), project, member.getRole())));
    }

    private void checkPermission(Project project) {
        if(!authenticationFacade.getUserEmail().equals(project.getWriter().getEmail()))
            throw new PermissionDeniedException();
    }


    private List<ProjectMemberDto> getProjectMemberList(Project project) {
        return memberRepository.findByProject(project).stream()
                .map(ProjectMemberDto::of)
                .collect(Collectors.toList());
    }

    private List<FieldEnum> getFieldEnumList(Project project) {
        return projectFieldRepository.findFieldEnumByProject(project);
    }

    private Project getProject(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);
    }

    private User getUserByMember(ProjectMemberDto member) {
        return userRepository.findByEmail(member.getEmail())
                .orElseThrow(MemberNotFoundException::new);
    }

    private Field getField(FieldEnum fieldEnum) {
        return fieldRepository.findByField(fieldEnum)
                .orElseThrow(FieldNotFoundException::new);
    }

}

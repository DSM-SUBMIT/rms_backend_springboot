package com.submit.toyproject.rms_backend_springboot.service.user;

import com.submit.toyproject.rms_backend_springboot.domain.field.ProjectField;
import com.submit.toyproject.rms_backend_springboot.domain.field.ProjectFieldRepository;
import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.response.*;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotFoundException;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ProjectFieldRepository projectFieldRepository;

    @Override
    public UsersResponse getUsers(String name) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotAuthenticatedException::new);
        List<User> users = userRepository.findByNameLike("%" + name + "%");
        return new UsersResponse(users.stream().map(
                user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build()
        ).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public MyPageResponse getMyPage() {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);
        List<Member> memberList = memberRepository.findByUser(user);

        List<ProjectListElementDto> projectList = new ArrayList<>();

        for (Member member : memberList) {
            Project project = member.getProject();
            List<ProjectField> projectFieldList = projectFieldRepository.findByProject(project);
            List<String> fieldList = projectFieldList.stream()
                    .map(projectField -> projectField.getField().getField().toString())
                    .collect(Collectors.toList());

            ProjectListElementDto projectResponse = ProjectListElementDto.builder()
                    .id(project.getId())
                    .projectName(project.getProjectName())
                    .teamName(project.getTeamName())
                    .projectType(project.getProjectType().toString())
                    .fieldList(fieldList)
                    .build();

            projectList.add(projectResponse);
        }

        return MyPageResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .projectList(projectList)
                .build();
    }
}
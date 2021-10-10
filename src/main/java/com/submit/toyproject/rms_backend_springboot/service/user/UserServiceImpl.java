package com.submit.toyproject.rms_backend_springboot.service.user;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.field.ProjectFieldRepository;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.request.StudentNumberRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.*;
import com.submit.toyproject.rms_backend_springboot.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final ProjectFieldRepository projectFieldRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public UsersResponse getUsers(String name) {
        authenticationFacade.certifiedUser();
        List<User> users = userRepository.findByNameLikeOrderByName("%" + name + "%");
        return new UsersResponse(users.stream().map(UserDto::of)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public MyPageResponse getMyPage() {
        User user = authenticationFacade.certifiedUser();
        List<ProjectListElementDto> projectDtoList = memberRepository.findProjectByUser(user).stream()
                .map(project -> ProjectListElementDto.of(project, getFieldEnumList(project)))
                .collect(Collectors.toList());

        return MyPageResponse.of(user, projectDtoList);
    }

    @Override
    public NameResponse getName() {
        return new NameResponse(authenticationFacade.certifiedUser().getName());
    }

    @Override
    @Transactional
    public void saveNumber(StudentNumberRequest request) {
        authenticationFacade.certifiedUser().saveNumber(request.getStudentNumber());
    }

    private List<FieldEnum> getFieldEnumList(Project project) {
        return projectFieldRepository.findFieldEnumByProject(project);
    }

}
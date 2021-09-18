package com.submit.toyproject.rms_backend_springboot;

import com.submit.toyproject.rms_backend_springboot.domain.field.*;
import com.submit.toyproject.rms_backend_springboot.domain.member.Member;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberId;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectType;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.response.MemberResponse;
import com.submit.toyproject.rms_backend_springboot.exception.NotFoundException;
import com.submit.toyproject.rms_backend_springboot.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Component
public class BasicTestSupport {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectFieldRepository projectFieldRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public MockMvc setUp() {
        return MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    public <T extends CrudRepository> void cleanUp(T repository) {
        repository.deleteAll();
    }

    public User createUser(String email, String name) {
        return userRepository.save(User.builder()
                .email(email)
                .name(name)
                .build());
    }

    public Project createProject(String projectName, User writer) {
        Project project = Project.builder()
                .projectName(projectName)
                .teamName("submit")
                .techStacks("Spring boot, ")
                .projectType(ProjectType.CLUB)
                .teacher("teacher")
                .githubUrl("githubUrl")
                .docsUrl("docsUrl")
                .serviceUrl("serviceUrl")
                .user(writer)
                .build();

        projectRepository.save(project);

//        for(String field : fieldList) {
//            addProjectField(project, FieldEnum.valueOf(field));
//        }
//
//        for(MemberResponse member : memberList) {
//            addMember(project, member.getEmail(), member.getName(), member.getRole());
//        }

        return project;
    }

    public Member addMember(Project project, User user, String role) {
        if(!memberRepository.findById(new MemberId(project.getId(), user.getId())).isEmpty()) {
            return null;
        }
        return memberRepository.save(
                Member.builder()
                        .project(project)
                        .user(user)
                        .role(role)
                        .build()
        );
    }

    public ProjectField addProjectField(Project project, FieldEnum fieldEnum) {
        if(fieldRepository.findByField(fieldEnum).isEmpty()) {
            fieldRepository.save(Field.builder()
                    .field(fieldEnum)
                    .build());
        }

        return projectFieldRepository.save(
                ProjectField.builder()
                        .project(project)
                        .field(fieldRepository.findByField(fieldEnum)
                        .orElseThrow(NotFoundException::new))
                        .build()
        );
    }
}

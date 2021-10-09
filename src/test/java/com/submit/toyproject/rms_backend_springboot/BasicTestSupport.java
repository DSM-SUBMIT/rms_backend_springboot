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
import com.submit.toyproject.rms_backend_springboot.exception.FieldNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

        fieldRepository.save(Field.builder().id(1).field(FieldEnum.WEB).build());
        fieldRepository.save(Field.builder().id(2).field(FieldEnum.APP).build());
        fieldRepository.save(Field.builder().id(3).field(FieldEnum.AI_BIGDATA).build());
        fieldRepository.save(Field.builder().id(4).field(FieldEnum.EMBEDDED).build());
        fieldRepository.save(Field.builder().id(5).field(FieldEnum.GAME).build());
        fieldRepository.save(Field.builder().id(6).field(FieldEnum.SECURITY).build());

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
                .teamName("testTeam")
                .techStacks("techStacks")
                .projectType(ProjectType.CLUB)
                .teacher("teacher")
                .githubUrl("githubUrl")
                .docsUrl("docsUrl")
                .serviceUrl("serviceUrl")
                .writer(writer)
                .build();

        projectRepository.save(project);

        return project;
    }

    public Member addMember(User user, Project project, String role) {
        if(memberRepository.findById(new MemberId(user.getId(), project.getId())).isPresent()) {
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

        return projectFieldRepository.save(
                ProjectField.builder()
                        .project(project)
                        .field(fieldRepository.findByField(fieldEnum)
                                .orElseThrow(FieldNotFoundException::new))
                        .build()
        );
    }
}

package com.submit.toyproject.rms_backend_springboot.domain.member;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, MemberId> {
    Boolean existsByProjectAndUser(Project project, User user);
    List<Member> findByUser(User user);
    List<Member> findByProject(Project project);
    void deleteAllByProject(Project project);
}

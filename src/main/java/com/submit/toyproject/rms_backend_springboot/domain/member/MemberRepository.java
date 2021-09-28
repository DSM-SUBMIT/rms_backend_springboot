package com.submit.toyproject.rms_backend_springboot.domain.member;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, MemberId> {
    Boolean existsByProjectAndUser(Project project, User user);

    @Query(value = "select m.project from Member m where m.user = :user")
    List<Project> findProjectByUser(@Param("user")User user);

    List<Member> findByProject(Project project);

    void deleteAllByProject(Project project);
}

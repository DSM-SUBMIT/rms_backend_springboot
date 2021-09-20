package com.submit.toyproject.rms_backend_springboot.domain.field;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectFieldRepository extends CrudRepository<ProjectField, ProjectFieldId> {
    @Query(value = "select DISTINCT r.project from ProjectField r where (:fields is null or r.field.field in (:fields)) " +
            "and r.project.status.isReportAccepted = true " +
            "order by r.project.status.reportSubmittedAt desc")
    Page<Project> findAllByFields(@Param("fields")List<FieldEnum> fields, Pageable pageable);

    List<ProjectField> findByProject(Project project);

    void deleteAllByProject(Project project);
}
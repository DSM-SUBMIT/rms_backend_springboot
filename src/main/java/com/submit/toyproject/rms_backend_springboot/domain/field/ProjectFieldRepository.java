package com.submit.toyproject.rms_backend_springboot.domain.field;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ProjectFieldRepository extends CrudRepository<ProjectField, ProjectFieldId> {
    @Query("select r from ProjectField r where (:fields is null or r.field.field in (:fields)) and r.project.status.reportSubmittedAt between :yearStart and :yearEnd " +
            "and r.project.status.isReportAccepted = true order by r.project.status.reportSubmittedAt desc")
    List<ProjectField> findAllByFieldAndYear(@Param("fields")List<Field> fields, @Param("yearStart") LocalDate date1, @Param("yearEnd") LocalDate date2);

    List<ProjectField> findByProject(Project project);

    void deleteAllByProject(Project project);
}
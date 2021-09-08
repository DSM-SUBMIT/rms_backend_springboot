package com.submit.toyproject.rms_backend_springboot.domain.field;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportFieldRepository extends CrudRepository<ProjectField, Integer> {
    @Query("select r from ProjectField r where (:fields is null or r.field.field in (:fields)) and r.project.report.status.acceptedAt between :yearStart and :yearEnd " +
            "and r.report.isPublic = true and r.report.status.isAccepted = true order by r.report.status.acceptedAt desc")
    List<ProjectField> findAllByFieldAndYear(@Param("fields")List<Field> fields, @Param("yearStart") LocalDate date1, @Param("yearEnd") LocalDate date2);
}
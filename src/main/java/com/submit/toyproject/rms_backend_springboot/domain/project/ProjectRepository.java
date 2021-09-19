package com.submit.toyproject.rms_backend_springboot.domain.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    @Query("select p from Project p " +
            "where p.status.isReportAccepted = true and p.status.isPlanAccepted = true " +
            "and p.id < :id ")
    Page<Project> findByIdLessThanAndReportAcceptedAndIsPlanAccepted(@Param("id") Integer id, Pageable pageable);

    @Query("select p from Project p inner join p.status s " +
            "where s.isReportAccepted = true and s.isPlanAccepted = true ")
    Page<Project> findAllByReportAcceptedAndPlanAccepted(Pageable pageable);
}

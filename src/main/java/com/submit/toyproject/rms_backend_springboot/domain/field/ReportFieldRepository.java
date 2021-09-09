package com.submit.toyproject.rms_backend_springboot.domain.field;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportFieldRepository extends CrudRepository<ProjectField, Integer> {
}
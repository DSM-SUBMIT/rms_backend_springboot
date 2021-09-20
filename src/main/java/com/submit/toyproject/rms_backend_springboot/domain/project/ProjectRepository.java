package com.submit.toyproject.rms_backend_springboot.domain.project;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    Optional<Project> findById(Integer id);
}

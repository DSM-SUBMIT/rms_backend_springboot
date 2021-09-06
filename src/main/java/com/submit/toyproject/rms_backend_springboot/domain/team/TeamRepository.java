package com.submit.toyproject.rms_backend_springboot.domain.team;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    Boolean existsByName(String name);
    List<Team> findByNameLike(String keyword);
}

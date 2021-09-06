package com.submit.toyproject.rms_backend_springboot.domain.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByNameLike(String name);
}

package com.submit.toyproject.rms_backend_springboot.domain.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByNameLike(String name);
    Optional<User> findByEmail(String email);
}

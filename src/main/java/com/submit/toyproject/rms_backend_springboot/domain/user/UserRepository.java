package com.submit.toyproject.rms_backend_springboot.domain.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "select * from user where name like ?1", nativeQuery = true)
    List<User> findByName(String name);
}

package com.submit.toyproject.rms_backend_springboot.domain.field;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FieldRepository extends CrudRepository<Field, Integer> {
    Optional<Field> findByField(FieldEnum field);
}

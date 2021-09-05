package com.submit.toyproject.rms_backend_springboot.domain.language;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, Integer> {
    Boolean existsByLanguage(String language);
    List<Language> findByLanguageLike(String language);
}

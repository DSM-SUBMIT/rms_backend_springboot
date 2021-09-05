package com.submit.toyproject.rms_backend_springboot.service.language;

import com.submit.toyproject.rms_backend_springboot.domain.language.Language;
import com.submit.toyproject.rms_backend_springboot.domain.language.LanguageRepository;
import com.submit.toyproject.rms_backend_springboot.exception.LanguageAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public void saveLanguage(String language) {
        //토큰 확인
        if (languageRepository.existsByLanguage(language)) {
            throw new LanguageAlreadyExistsException();
        } else {
            languageRepository.save(new Language(language));
        }
    }

}

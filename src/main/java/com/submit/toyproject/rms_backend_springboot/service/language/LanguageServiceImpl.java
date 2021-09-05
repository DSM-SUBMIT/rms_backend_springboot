package com.submit.toyproject.rms_backend_springboot.service.language;

import com.submit.toyproject.rms_backend_springboot.domain.language.Language;
import com.submit.toyproject.rms_backend_springboot.domain.language.LanguageRepository;
import com.submit.toyproject.rms_backend_springboot.dto.response.LanguageDto;
import com.submit.toyproject.rms_backend_springboot.dto.response.LanguagesResponse;
import com.submit.toyproject.rms_backend_springboot.exception.LanguageAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public LanguagesResponse getLanguages(String keyword) {
        //토큰 확인
        List<Language> languages = languageRepository.findByLanguageLike(keyword);
        return new LanguagesResponse(languages.stream().map(
                language -> LanguageDto.builder()
                        .id(language.getId())
                        .language(language.getLanguage())
                        .build()
        ).collect(Collectors.toList()));
    }

}

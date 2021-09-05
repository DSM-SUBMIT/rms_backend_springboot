package com.submit.toyproject.rms_backend_springboot.service.language;

import com.submit.toyproject.rms_backend_springboot.dto.response.LanguagesResponse;

public interface LanguageService {
    void saveLanguage(String language);
    LanguagesResponse getLanguages(String keyword);
}

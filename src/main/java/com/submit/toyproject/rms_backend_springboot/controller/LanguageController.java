package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.LanguageRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.LanguagesResponse;
import com.submit.toyproject.rms_backend_springboot.service.language.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/language")
@RestController
public class LanguageController {

    private final LanguageService languageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveLanguage(@RequestBody @Valid LanguageRequest request) {
        languageService.saveLanguage(request.getLanguage());
    }

    @GetMapping
    public LanguagesResponse getLanguages(@RequestParam(defaultValue = "%%") String keyword) {
        return languageService.getLanguages(keyword);
    }

}

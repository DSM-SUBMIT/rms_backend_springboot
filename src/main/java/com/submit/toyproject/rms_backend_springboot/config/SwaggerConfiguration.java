package com.submit.toyproject.rms_backend_springboot.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

import static java.util.Collections.singletonList;

@EnableSwagger2
@RequiredArgsConstructor
@Configuration
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    private final TypeResolver typeResolver;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver();
        pageableArgumentResolver.setOneIndexedParameters(true);     // 페이지 인덱스 1부터 시작
        pageableArgumentResolver.setFallbackPageable(PageRequest.of(0, 5));
        argumentResolvers.add(pageableArgumentResolver);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class)
                                , typeResolver.resolve(Page.class)))
                .consumes(setContentType())
                .produces(setContentType())
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.submit.toyproject.rms_backend_springboot.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContext())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private List<SecurityContext> securityContext() {
        List<SecurityContext> securityContexts = new ArrayList<>();

        String[] paths = {
                "", "/plan.*", "/project.*", "/report.*", "/me", "/search"
        };

        for(String path : paths) {
            securityContexts.add(SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.regex(path))
                    .build());
        }

        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("auth/** 제외 모든것", "accessEverything without auth");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    private Set<String> setContentType() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        return consumes;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("RMS API")
                .description("보고서 관리 시스템 API 명세입니다.")
                .build();
    }

    @ApiModel
    static class Page {
        private int page;

        private int size;
    }

}

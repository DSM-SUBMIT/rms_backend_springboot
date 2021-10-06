package com.submit.toyproject.rms_backend_springboot.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public GroupedOpenApi SecurityGroupOpenApi() {
        return GroupedOpenApi.builder()
                .group("Security Open Api")
                .pathsToExclude("/auth/*")
                .addOpenApiCustomiser(buildSecurityOpenApi())
                .build();
    }

    @Bean
    public GroupedOpenApi NoSecurityGroupOpenApi() {
        return GroupedOpenApi.builder()
                .group("Non Security Open Api")
                .pathsToMatch("/auth/*")
                .build();
    }

    private OpenApiCustomiser buildSecurityOpenApi() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .bearerFormat("JWT")
                .scheme("Bearer");

        return OpenApi -> OpenApi
                .addSecurityItem(new SecurityRequirement().addList("jwt token"))
                .getComponents().addSecuritySchemes("jwt token", securityScheme);
    }

}

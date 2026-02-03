package com.sample.music.controller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Swagger UI display.
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Sample Music Product API").version("1.0").
                        description("A sample music product API to demonstrate the use " +
                                "of custom JPA queries and swagger annotations"));
    }

}

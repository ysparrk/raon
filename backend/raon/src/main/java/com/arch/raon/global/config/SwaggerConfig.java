package com.arch.raon.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Raon Api Docoument",
                description = "api명세",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

}

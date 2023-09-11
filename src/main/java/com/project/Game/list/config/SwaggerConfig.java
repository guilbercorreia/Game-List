package com.project.Game.list.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Game List",
        version = "1.0",
        description = "API para gerenciar os jogos",
        contact = @Contact(name = "Guilber Correia")))

public class SwaggerConfig {
}

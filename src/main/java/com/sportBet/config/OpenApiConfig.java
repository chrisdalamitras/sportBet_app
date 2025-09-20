package com.sportBet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sportBetOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SportBet API")
                        .description("REST API for managing Matches and MatchOdds")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("SportBet Dev Team")
                                .email("support@sportbet.com")));
    }

}

package com.ronay.basic.bank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Basic Bank API")
                        .version("1.0.0")
                        .description("Basic Bank Project Documentation with SpringDoc")
                        .contact(new Contact()
                                .name("Ronay Bank")
                                .email("")));
    }
}
package br.com.fiap.catalogo.configuration.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalogo Challenge")
                        .version("v1")
                        .description("Catalogo Challenge")
                        .termsOfService("https://github.com/Tatu-Armadillo/catalogo")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/Tatu-Armadillo/catalogo")));
    }

}

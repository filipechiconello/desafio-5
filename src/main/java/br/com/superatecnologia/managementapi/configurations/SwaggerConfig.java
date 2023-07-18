package br.com.superatecnologia.managementapi.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.app.name}")
    private String name;

    @Value("${swagger.app.version}")
    private String version;

    @Value("${swagger.app.description}")
    private String description;

    @Value("${swagger.base.package}")
    private String basePackage;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.contact.email}")
    private String contactEmail;

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group(name)
                .packagesToScan(basePackage)
                .build();
    }

    @Bean
    public OpenAPI playDeliveriesOpenApi() {
        return new OpenAPI()
                .info(new Info().contact(new Contact().name(contactName).email(contactEmail))
                        .description(description)
                        .version(version)
                        .title(name));
    }
}

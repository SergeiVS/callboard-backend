package org.callboard.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
//    @Bean
//    public GroupedOpenApi publicAPI() {
//        return GroupedOpenApi.builder()
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi privateAPI() {
//        return GroupedOpenApi.builder()
//                .build();
//    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info( new Info()
                                .title("Callboard project")
                                .version("v1")
                                .description("Used APIs description"))
                .components(new Components()
                        .addSecuritySchemes("Bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                                ))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"));
    }
}

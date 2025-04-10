package ma.kata.product_app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        ApiResponse notFoundResponse = new ApiResponse().description("Ressource non trouvée (404)");
        ApiResponse serverErrorResponse = new ApiResponse().description("Erreur interne du serveur (500)");
        ApiResponse unauthorizedResponse = new ApiResponse().description("Non autorisé (401)");

        SecurityScheme basicAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .description("Authentification via JWT token");

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("basicAuth", basicAuthScheme)
                        .addResponses("404", notFoundResponse)
                        .addResponses("500", serverErrorResponse)
                        .addResponses("401", unauthorizedResponse))
                .info(new Info()
                        .title("Documentation API - Product App")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Othman FREJ")
                                .email("othman2020frej@gmail.ma")
                                .url("https://test.ma"))
                );
    }
}


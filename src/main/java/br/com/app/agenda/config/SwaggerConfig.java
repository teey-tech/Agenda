package br.com.app.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI springBlogPessoalOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("API REST: Agendamento")
            .description("Uma API REST que controla os agendamentos de pacientes")
            .version("v1.0.0")
            .license(new License()
                .name("Thiago")
                .url("<https://github.com/teey-tech"))
            .contact(new Contact()
                .name("Github - Thiago")
                .url("<https://github.com/teey-tech/>")))
        .externalDocs(new ExternalDocumentation()
            .description("Github - Projeto")
            .url("<https://github.com/teey-tech/>"));
  }

  @Bean
  public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

    return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

      ApiResponses apiResponses = operation.getResponses();

      apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
      apiResponses.addApiResponse("201", createApiResponse("Criado com Sucesso!"));
      apiResponses.addApiResponse("204", createApiResponse("Não contem!"));
      apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
      apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
      apiResponses.addApiResponse("404", createApiResponse("Não encontramos nada!"));
      apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

    }));
  }

  private ApiResponse createApiResponse(String message) {

    return new ApiResponse().description(message);

  }
}

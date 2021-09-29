package com.telefonica.msqueryinternalqualification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Class for initial configuration swagger for documentation about microservices
 * @author dpanquev
 * @version 2021-06-10
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Collections.singleton("application/json"))
                .consumes(Collections.singleton("application/json"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.telefonica.msqueryinternalqualification"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }
	
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Endpoint Calificaciones").description("Microservicio para consultar y descargar calificaciones por canal")
                .contact(new Contact("Organización Telefónica", "https://www.telefonica.co/", "william.londono@telefonica.com")).license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("0.1.0").build();
    }
}

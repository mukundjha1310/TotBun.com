package com.totbun;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TotBunApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotBunApiApplication.class, args);
	}

    @Bean
    Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .securityContexts(Arrays.asList(securContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.totbun"))
                .build().apiInfo(apiDetails());
    }

	private ApiInfo apiDetails() {
		return new ApiInfo("TotBun.com", 
				"REST API for Ecommerce website TotBun.com", "0.0.1-SNAPSHOT",
				"Open Source API",
				new springfox.documentation.service.Contact("Mukund Jha",
				"https://mukundjha1310.github.io/mukundjha.github.io/", "mukundjha1310@gmail.com"),
				"Copyright © 2022 Jha1310Mukund. All Rights Reserved", "https://TotBun.com", 
				Collections.emptyList());
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", SecurityScheme.In.HEADER.name());
	}

	private SecurityContext securContext() {
		return SecurityContext.builder().securityReferences(securityReference()).build();
	}

	private List<SecurityReference> securityReference() {
		AuthorizationScope[] authorizationScope = { new AuthorizationScope("Globle", "Full API Permission") };
		return Arrays.asList(new SecurityReference("JWT", authorizationScope));
	}

}

package com.totbun;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TotBunApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotBunApiApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfig()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.totbun")).build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo(
				"TotBun.com", 
				"REST API for Ecommerce website TotBun.com", 
				"0.0.1-SNAPSHOT", 
				"Open Source API", 
				new springfox.documentation.service.Contact("Mukund Jha", "https://mukundjha1310.github.io/mukundjha.github.io/", "mukundjha1310@gmail.com"), 
				"Copyright © 2022 Jha1310Mukund. All Rights Reserved", 
				"https://TotBun.com",
				Collections.emptyList());
	}

}

package org.pagsousa.ecafeteriaxxi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 *
 * @author Paulo Gandra de Sousa
 *
 */
@Configuration
public class ApiConfig {

	/**
	 * support for etags
	 *
	 * @return
	 */
	@Bean
	public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}

	/*
	 * OpenAPI
	 */
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI().info(new Info().title("eCafeteria XXI API").description(
				"REST API for eCafeteria XXI demo project with SpringBoot, SpringData/JPA following Domain-Driven Design (DDD) principles")
				.version("1.0").contact(new Contact().name("Paulo Sousa").email("pag@isep.ipp.pt"))
				.termsOfService("TOC").license(new License().name("MIT").url("#")));
	}
}

package com.nts.campaignservice.config.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nts.campaignservice"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "UOL Customer API",
                        "",
                        "v0",
                        "",
                        new Contact("Gabriel Kirsten Menezes", "https://gabrielkirsten.me", "gabriel.kirsten@hotmail.com"),
                        "",
                        "",
                        Collections.emptyList()
                ));
    }
}

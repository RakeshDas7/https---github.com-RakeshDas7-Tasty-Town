// this a costume bean for swagger Api


package com.tastytown.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean // use of @Bean :- override pre-define class and interface costume object 
    OpenAPI openAPI(){ // OpenAPI s a class for swagger so it's a factory method.
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo(){
        var info = new Info().title("Tasty Town").version("v2").description("A Web Application for ordering Foods");
        return info;
    }
}

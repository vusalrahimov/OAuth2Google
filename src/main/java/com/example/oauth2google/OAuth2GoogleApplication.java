package com.example.oauth2google;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OAuth2GoogleApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2GoogleApplication.class, args);
    }


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

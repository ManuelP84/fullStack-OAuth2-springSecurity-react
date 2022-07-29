package com.springsecurity.authServerSpringSecurity.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
public class CorsCustomizer {

    public  void corsCustomizer(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowedOrigins(List.of("http://127.0.0.1:3000"));
                corsConfig.setAllowedHeaders(List.of("*"));
                corsConfig.setAllowedMethods(List.of("*"));
                return corsConfig;
            };
            c.configurationSource(source);
        });
    }
}

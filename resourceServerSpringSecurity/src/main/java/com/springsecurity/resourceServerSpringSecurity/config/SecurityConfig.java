package com.springsecurity.resourceServerSpringSecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        corsCustomizer.corsCustomizer(httpSecurity);
        return httpSecurity
                .oauth2ResourceServer(j -> j.jwt().jwkSetUri("http://localhost:8080/oauth2/jwks"))
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }
}
package com.springsecurity.authServerSpringSecurity.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.springsecurity.authServerSpringSecurity.security.keys.JwKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.RSAKey;
import java.time.Duration;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    private final CorsCustomizer corsCustomizer;

    // http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://127.0.0.1:3000/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256
    // http://localhost:8080/oauth2/token?client_id=client&redirect_uri=http://127.0.0.1:3000/authorized&grant_type=authorization_code&code=LmAs-fk_v2DDRd5aMeIsd2XzU539m2AT8DrlXFCAcM2JcTuuDvCEUbbv_ovUO8Rp4ZJeajYq11dBoCm-_zVCqVtriDqctdkUCBE2jEdXlSqQ5BDn9PIvmd4NpdUYT2Gq&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI&scope=openid

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityAuthServerFilterChain(HttpSecurity httpSecurity) throws Exception {
        corsCustomizer.corsCustomizer(httpSecurity);
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        return httpSecurity.formLogin().and().build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(){
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client")
                .clientSecret("secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:3000/authorized")
                .scope(OidcScopes.OPENID)
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .build())
                .tokenSettings(TokenSettings.builder()
                                .refreshTokenTimeToLive(Duration.ofHours(10))
                                .build())
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    @Bean
    public ProviderSettings providerSettings(){
        return ProviderSettings
                .builder()
                .issuer("http://localhost:8080")
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(){
        RSAKey rsaKey = JwKeys.generateRSAKey();
        JWKSet set = new JWKSet(rsaKey);
        return (j, sc) -> j.select(set);
    }
}

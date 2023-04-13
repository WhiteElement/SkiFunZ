package com.manu.BergfexScraper.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests().requestMatchers("api/v1/**").permitAll()
                .and()
                .csrf().disable()
                .headers(headers ->
                        headers
                                .contentSecurityPolicy(contentSecurityPolicy ->
                                        contentSecurityPolicy
                                                .policyDirectives("default-src 'self'")));
        return http.build();
    }
}

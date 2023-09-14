package com.libraryapp.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        // Protect endpoints at /api/books/secure/<type>
        http.authorizeHttpRequests(configurer ->
                configurer
                    .antMatchers("/api/books/secure/**")
                    .authenticated()
                    .anyRequest().permitAll())
                    .oauth2ResourceServer().jwt();


        // Disable Cross Site Request Forgery
        http.csrf().disable();

        // Cors filter
        http.cors();

        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make response friendly
        Okta.configureResourceServer401ResponseBody(http);


        return http.build();
    }
}

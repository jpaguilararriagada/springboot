package com.jota.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        try {
            //Todas las peticiones deberian estar autenticadas
            http.authorizeHttpRequests(
                    auth -> auth.anyRequest().authenticated()
            );
            // Si no esta autenticado se muestra un popup
            http.httpBasic(Customizer.withDefaults());

            //disable csrf
            http.csrf().disable();
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

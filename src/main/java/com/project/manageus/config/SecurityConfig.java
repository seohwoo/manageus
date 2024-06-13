package com.project.manageus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/register", "/forgot",
                                "/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**").permitAll()
                        .requestMatchers("/super/**").hasRole("SUPER")
                        .requestMatchers("/admin/**").hasAnyRole("SUPER", "ADMIN")
                        .anyRequest().permitAll()
                // permitAll() 보안 없이 들어갈 수 있는거
                // authenticated() - 보안 돼있는거
        );

        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/user/loginProc")
                .permitAll()
        );

        http.csrf((auth) -> auth.disable());


        return http.build();
    }



}

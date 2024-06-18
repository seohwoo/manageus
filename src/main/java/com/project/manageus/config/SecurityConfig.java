package com.project.manageus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/register", "/forgot", "/company",
                        "/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**").permitAll()
              //  .requestMatchers("/super/**").hasRole("SUPER")
               // .requestMatchers("/admin/**").hasAnyRole("SUPER", "ADMIN")
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/board/**").permitAll()
                .requestMatchers("/chat").permitAll()
                .requestMatchers("/ws/**").permitAll()
                .anyRequest().permitAll()
        );

        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/user/loginProc")
                .permitAll()
        );

        http.csrf((auth) -> auth.disable());


        return http.build();
    }



}

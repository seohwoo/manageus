package com.project.manageus.config;

import com.project.manageus.handler.SecurityHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityHandler securityHandler;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/register", "/forgot", "/company",
                        "/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**").permitAll()
               // .requestMatchers("/super/**").hasRole("SUPER")
               // .requestMatchers("/admin/**").hasAnyRole("SUPER", "ADMIN")
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/board/**").permitAll()
                .requestMatchers("/chat").permitAll()
                .requestMatchers("/ws/**").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .permitAll()
        );
        http.csrf((auth) -> auth.disable());
        //다중 로그인 설정(2개까지 가능)
        http.sessionManagement((auth) -> auth
                .maximumSessions(2)
                .maxSessionsPreventsLogin(false));
        //세션 보호 목적
        http.sessionManagement((auth) -> auth
                .sessionFixation().changeSessionId());
        //Logout
        http.logout((auth) -> auth.logoutUrl("/logout")
                .logoutSuccessUrl("/"));
        //error
        http.exceptionHandling((auth) -> auth
                .accessDeniedHandler(securityHandler)
                .authenticationEntryPoint((request, response, authException) ->
                        response.sendRedirect("/login?error=true"))
        );
        return http.build();

    }


}

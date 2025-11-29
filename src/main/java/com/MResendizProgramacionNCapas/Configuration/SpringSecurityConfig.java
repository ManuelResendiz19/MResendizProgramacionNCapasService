package com.MResendizProgramacionNCapas.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.MResendizProgramacionNCapas.Service.UsuarioDetailsJPAService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final UsuarioDetailsJPAService usuarioDetailsJPAService;

    public SpringSecurityConfig(UsuarioDetailsJPAService usuarioDetailsJPAService1) {
        this.usuarioDetailsJPAService = usuarioDetailsJPAService1;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/api/auth/login").permitAll()
                .anyRequest().authenticated()).addFilterBefore(filter, beforeFilter);
                
                .formLogin(form -> form
                .loginPage("/api/auth/login").
//                .successHandler((request, response, authentication) -> {
//                    String redirectUrl = "/default";
//                    if (authentication.getAuthorities().stream()
//                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//                    redirectUrl = "http://localhost:8081/usuario"; // va a la ruta GetAll del cliente, lo puse esto comentado para no perderme :b
//                } else {
//                    redirectUrl = "http://localhost:8081/detail"; // van a la ruta Detal en el cliente
//                }
//                    response.sendRedirect(redirectUrl);
//                })
                        .usernameParameter("username").
                        .passwordEncoder("password")
                        .defaultSuccessUrl("/home", true)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

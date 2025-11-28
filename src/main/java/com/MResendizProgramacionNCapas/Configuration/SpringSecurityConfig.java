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

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/api/auth/login").permitAll()
                .anyRequest().permitAll()
//                //                .anyRequest()
//                .authenticated())
//                .formLogin(form -> form
//                .defaultSuccessUrl("/usuario", true)
                ).httpBasic(Customizer.withDefaults())
                .userDetailsService(usuarioDetailsJPAService);

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

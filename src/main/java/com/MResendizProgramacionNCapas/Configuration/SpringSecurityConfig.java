package com.MResendizProgramacionNCapas.Configuration;

import com.MResendizProgramacionNCapas.JWT.JwtAuthFilter;
import com.MResendizProgramacionNCapas.Service.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.MResendizProgramacionNCapas.Service.UsuarioDetailsJPAService;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final UsuarioDetailsJPAService usuarioDetailsJPAService;
    private final SecurityConfigProperties securityConfigProperties;

    public SpringSecurityConfig(SecurityConfigProperties securityConfigProperties,UsuarioDetailsJPAService usuarioDetailsJPAService) {
        this.securityConfigProperties = securityConfigProperties;
        this.usuarioDetailsJPAService = usuarioDetailsJPAService;        
    }
   
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {

        http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/usuario/detail").hasAnyRole("SUPERVISOR", "USUARIO", "CLIENTE")
                .requestMatchers("/usuario").hasRole( "ADMIN")
                
                .anyRequest().authenticated()                       
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);       
                

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
    
    
    private AuthenticationProvider authenticationProvider(){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(usuarioDetailsJPAService);
        return provider;
    }
}

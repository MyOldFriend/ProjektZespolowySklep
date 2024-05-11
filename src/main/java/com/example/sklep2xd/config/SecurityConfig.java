package com.example.sklep2xd.config;

import com.example.sklep2xd.Service.impl.KlientServiceimpl;
import com.example.sklep2xd.ssecurity.JwtAuthenticationFilter;
import com.example.sklep2xd.ssecurity.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enable method level security

public class SecurityConfig {
    private final KlientServiceimpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(KlientServiceimpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/logowanie", "/home").permitAll() // Public paths
                        .requestMatchers("/Pracownik/dzialzamowien").hasAuthority("ROLE_PRACOWNIK") // Restricted to ROLE_PRACOWNIK
                        .requestMatchers("/klienci/konto").hasAuthority("ROLE_KLIENT") // Restricted to ROLE_KLIENT
                        .anyRequest().authenticated() // All other requests must be authenticated
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Ensure JwtAuthenticationFilter is correctly autowired

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(jwtUtil()); // Ensure JwtUtil is autowired into the filter
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    // Define PasswordEncoder as a Bean
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.example.sklep2xd.Config;

import com.example.sklep2xd.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/pracownik/**").hasRole("EMPLOYEE")
                        .requestMatchers("/klient/**").hasRole("KLIENT")
                        .requestMatchers("/sendNewsletter").hasRole("KLIENT")
                        .requestMatchers("/Recenzja/usun/**").hasRole("ADMIN")
                        .requestMatchers("/Produkt/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/logowanie")
                        .permitAll()
                        .loginProcessingUrl("/logowanie")
                        .usernameParameter("login")
                        .passwordParameter("haslo")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureUrl("/logowanie?error=true")
                )
                .logout((logout) -> logout
                        .permitAll()
                )
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        return http.build();
    }

}

package com.example.sklep2xd.Config;

import com.example.sklep2xd.Service.MyUserDetailsService;
import jakarta.servlet.http.HttpSessionListener;
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
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
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

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/pracownik/**").hasRole("PRACOWNIK")
//                        .requestMatchers("/klient/**").hasRole("KLIENT")
//                        .anyRequest().permitAll()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/logowanie")
//                        .permitAll()
//                        .loginProcessingUrl("/logowanie")
//                        .usernameParameter("login")
//                        .passwordParameter("haslo")
//                        .successHandler(customAuthenticationSuccessHandler)
//                        .failureUrl("/logowanie?error=true")
//                )
//                .logout((logout) -> logout
//                        .permitAll()
//                )
//                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
//
//        return http.build();
//    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new ChangeSessionIdAuthenticationStrategy();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/pracownik/**").hasRole("PRACOWNIK")
//                        .requestMatchers("/klient/**").hasRole("KLIENT")
//                        .anyRequest().permitAll()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/logowanie")
//                        .permitAll()
//                        .loginProcessingUrl("/logowanie")
//                        .usernameParameter("login")
//                        .passwordParameter("haslo")
//                        .successHandler(customAuthenticationSuccessHandler)
//                        .failureUrl("/logowanie?error=true")
//                )
//                .logout((logout) -> logout
//                        .permitAll()
//                )
//                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
//                );
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/pracownik/**").hasRole("PRACOWNIK")
                        .requestMatchers("/klient/**").hasRole("KLIENT")
                        .requestMatchers("/sendNewsletter").hasRole("KLIENT")
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
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
                );

        return http.build();
    }


    @Bean
    public HttpSessionListener httpSessionListener(){
        return new SessionListener();
    }
}

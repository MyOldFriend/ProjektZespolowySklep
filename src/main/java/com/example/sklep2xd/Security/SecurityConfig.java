package com.example.sklep2xd.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private PracownikDetailsService pracownikDetailsService;

    @Autowired
    public SecurityConfig(PracownikDetailsService pracownikDetailsService) {
        this.pracownikDetailsService = pracownikDetailsService;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests((authorizeRequests)->
                        authorizeRequests
                                //przerobić jak będzie wyciąganie id z JWT
                                .requestMatchers("/home","/home/**", "/Produkt/lista2", "/Produkt/lista2/**", "/auth/rejestracjaPracownika").permitAll() //dostęp dla wsztskich
                                                                                                // zamienić rejestrację pracownika na rejestrację klienta jak będzie gotowa
                                                                                                // a rejestrację pracownika dać na admina
                                .requestMatchers("/klienci/edytuj/**").hasRole("KLIENT") //dostęp dla klienta
                                .requestMatchers("/Pracownik/dzialzamowien").hasRole("PRACOWNIK") //dostęp dla pracownika
                                .requestMatchers("/Pracownik/administrator").hasRole("ADMIN") //dostęp dla pracownika admina

                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
   
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

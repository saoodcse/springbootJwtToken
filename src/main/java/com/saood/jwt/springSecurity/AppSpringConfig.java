package com.saood.jwt.springSecurity;

import com.saood.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSpringConfig {

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless JWT
                .and()
                // Configure authorization to bypass all requests
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/**").permitAll()
                                .anyRequest().authenticated()// Allow all requests with authentication
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Add the JWT filter

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // Use custom authentication entry point
                .and()
                // Disable HTTP Basic authentication (commonly used for APIs)
                .httpBasic().disable()

                // Disable Form Login (for web applications with forms)
                .formLogin().disable();

        return http.build();
    }
}

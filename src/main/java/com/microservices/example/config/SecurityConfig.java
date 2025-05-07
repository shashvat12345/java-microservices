package com.microservices.example.config; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
@Configuration 
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
    @Override 
    protected void configure(HttpSecurity http) throws Exception { 
        http 
            .csrf().and() // Enable CSRF protection 
            .authorizeRequests() 
            .anyRequest().permitAll(); // Allow all requests for simplicity 
    } 
} 
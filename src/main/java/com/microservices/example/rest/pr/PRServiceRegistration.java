package com.microservices.example.rest.pr; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; 
@SpringBootApplication 
@EnableDiscoveryClient 
public class PRServiceRegistration { 
    public static void main(String[] args) { 
        System.setProperty("spring.config.name", "pr-service"); // Read properties from pr-service.properties 
        SpringApplication.run(PRServiceRegistration.class, args); 
    } 
} 
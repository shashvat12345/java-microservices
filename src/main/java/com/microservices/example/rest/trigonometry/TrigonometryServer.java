package com.microservices.example.rest.trigonometry; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration; 
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; 
import org.springframework.context.annotation.ComponentScan; 
@EnableAutoConfiguration 
@EnableDiscoveryClient 
@ComponentScan 
public class TrigonometryServer { 
    public static void main(String[] args) { 
        System.setProperty("spring.config.name", "trigonometry-server"); // Read properties from trigonometry-server.properties 
        SpringApplication.run(TrigonometryServer.class, args); 
    } 
}
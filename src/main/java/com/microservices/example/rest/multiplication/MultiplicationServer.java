package com.microservices.example.rest.multiplication; 
import java.util.logging.Logger; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration; 
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; 
import org.springframework.context.annotation.ComponentScan; 
@EnableAutoConfiguration 
@EnableDiscoveryClient 
@ComponentScan 
public class MultiplicationServer { 
    protected Logger logger = Logger.getLogger(MultiplicationServer.class.getName()); 
    public static void main(String[] args) { 
        System.setProperty("spring.config.name", "multiplication-server"); // Read properties from multiplication-server.properties 
        SpringApplication.run(MultiplicationServer.class, args); 
    } 
} 
package com.microservices.example.rest.division; 
import java.util.logging.Logger; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration; 
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; 
import org.springframework.context.annotation.ComponentScan; 
@EnableAutoConfiguration 
@EnableDiscoveryClient 
@ComponentScan 
public class DivisionServer { 
    protected Logger logger = Logger.getLogger(DivisionServer.class.getName()); 
    public static void main(String[] args) { 
        System.setProperty("spring.config.name", "division-server"); 
        SpringApplication.run(DivisionServer.class, args); 
    } 
} 
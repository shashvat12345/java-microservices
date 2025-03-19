package com.microservices.example.rest.division; 
import java.util.logging.Logger; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration; 
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; 
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@EnableAutoConfiguration 
@EnableDiscoveryClient 
@ComponentScan 
@RestController 
public class DivisionServer { 
    protected Logger logger = Logger.getLogger(DivisionServer.class.getName()); 
    public static void main(String[] args) { 
        System.setProperty("spring.config.name", "division-server"); // Read properties from division-server.properties 
        SpringApplication.run(DivisionServer.class, args); 
    } 
    @RequestMapping("/divide") 
    public String doDivide(@RequestParam(defaultValue="1") String dividend, 
                           @RequestParam(defaultValue="1") String divisor) { 
        try { 
            double div = Double.valueOf(dividend) / Double.valueOf(divisor); 
            logger.info("Division result is " + div); 
            return "{\"dividend\":\"" + dividend + "\", \"divisor\":\"" + divisor + "\", \"result\": \"" + div + "\"}"; 
        } catch (ArithmeticException e) { 
            logger.severe("Error during division: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"Division by zero is not allowed\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid number format: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"Invalid input format\"}"; 
        } catch (Exception e) { 
            logger.severe("Unexpected error: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An unexpected error occurred\"}"; 
        } 
    } 
}
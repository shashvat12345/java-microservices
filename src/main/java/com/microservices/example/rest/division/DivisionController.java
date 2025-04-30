package com.microservices.example.rest.division; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class DivisionController { 
    protected Logger logger = Logger.getLogger(DivisionController.class.getName()); 
    @RequestMapping("/divide") 
    public String doDivide(@RequestParam(defaultValue="0") String dividend, 
                           @RequestParam(defaultValue="1") String divisor) { 
        try { 
            int dividendValue = Integer.valueOf(dividend); 
            int divisorValue = Integer.valueOf(divisor); 
            if (divisorValue == 0) { 
                logger.severe("Division by zero attempted."); 
                return "{\"error\":\"Division by zero is not allowed.\"}"; 
            } 
            int quotient = dividendValue / divisorValue; 
            logger.info("Division result is " + quotient); 
            return "{\"dividend\":\"" + dividend + "\", \"divisor\":\"" + divisor + "\", \"quotient\": \"" + quotient + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid input: " + e.getMessage()); 
            return "{\"error\":\"Invalid input, please provide valid integers.\"}"; 
        } catch (Exception e) { 
            logger.severe("An error occurred: " + e.getMessage()); 
            return "{\"error\":\"An unexpected error occurred.\"}"; 
        } 
    } 
} 
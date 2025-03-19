package com.microservices.example.rest.multiplication; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class MultiplicationController { 
    protected Logger logger = Logger.getLogger(MultiplicationController.class.getName()); 
    @RequestMapping("/multiply") 
    public String doMultiply(@RequestParam(defaultValue="1") String multiplicand, 
                             @RequestParam(defaultValue="1") String multiplier) { 
        try { 
            int product = Integer.valueOf(multiplicand) * Integer.valueOf(multiplier); 
            logger.info("Multiplication result is " + product); 
            return "{\"multiplicand\":\"" + multiplicand + "\", \"multiplier\":\"" + multiplier + "\", \"result\": \"" + product + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid number format: " + e.getMessage()); 
            logger.severe("Stack trace: " + e.getStackTrace()); 
            return "{\"error\":\"Invalid input format\"}"; 
        } catch (Exception e) { 
            logger.severe("Unexpected error: " + e.getMessage()); 
            logger.severe("Stack trace: " + e.getStackTrace()); 
            return "{\"error\":\"An unexpected error occurred\"}"; 
        } 
    } 
}
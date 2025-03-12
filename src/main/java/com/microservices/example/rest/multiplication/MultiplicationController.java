package com.microservices.example.rest.multiplication; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class MultiplicationController { 
    protected Logger logger = Logger.getLogger(MultiplicationController.class.getName()); 
    @RequestMapping("/multiply") 
    public String doMultiply(@RequestParam(defaultValue="0") String multiplicand, 
                             @RequestParam(defaultValue="0") String multiplier) { 
        try { 
            int m1 = Integer.valueOf(multiplicand); 
            int m2 = Integer.valueOf(multiplier); 
            int product = m1 * m2; 
            logger.info("Multiplication result: " + product); 
            return "{\"multiplicand\":\"" + multiplicand + "\", \"multiplier\":\"" + multiplier + "\", \"product\": \"" + product + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid input: " + e.getMessage()); 
            return "{\"error\":\"Invalid input, please provide valid integers.\"}"; 
        } catch (Exception e) { 
            logger.severe("An error occurred: " + e.getMessage()); 
            logger.severe("Stack Trace: " + java.util.Arrays.toString(e.getStackTrace())); 
            return "{\"error\":\"An unexpected error occurred.\"}"; 
        } 
    } 
}
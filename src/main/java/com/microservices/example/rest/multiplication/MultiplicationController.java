package com.microservices.example.rest.multiplication; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class MultiplicationController { 
    protected Logger logger = Logger.getLogger(MultiplicationController.class.getName()); 
    @RequestMapping("/multiply") 
    public String doMultiply(@RequestParam(defaultValue = "1") String factor1, 
                             @RequestParam(defaultValue = "1") String factor2) { 
        try { 
            int multiplicand1 = Integer.valueOf(factor1); 
            int multiplicand2 = Integer.valueOf(factor2); 
            int product = multiplicand1 * multiplicand2; 
            logger.info("Multiplication result is " + product); 
            return "{\"factor1\":\"" + factor1 + "\", \"factor2\":\"" + factor2 + "\", \"product\": \"" + product + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid input: " + e.getMessage()); 
            return "{\"error\":\"Invalid input, please provide valid integers.\"}"; 
        } catch (Exception e) { 
            logger.severe("An error occurred: " + e.getMessage()); 
            e.printStackTrace(); // Log the stack trace for debugging 
            return "{\"error\":\"An unexpected error occurred.\"}"; 
        } 
    } 
}
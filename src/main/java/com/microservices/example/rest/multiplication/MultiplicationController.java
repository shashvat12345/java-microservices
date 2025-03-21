package com.microservices.example.rest.multiplication; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class MultiplicationController { 
    protected Logger logger = Logger.getLogger(MultiplicationController.class.getName()); 
    private final MultiplicationService multiplicationService; 
    public MultiplicationController(MultiplicationService multiplicationService) { 
        this.multiplicationService = multiplicationService; 
    } 
    @RequestMapping("/multiply") 
    public String doMultiply(@RequestParam(defaultValue = "0") String factor1, 
                             @RequestParam(defaultValue = "0") String factor2) { 
        try { 
            int multiplicand1 = Integer.valueOf(factor1); 
            int multiplicand2 = Integer.valueOf(factor2); 
            int product = multiplicationService.multiply(multiplicand1, multiplicand2); 
            logger.info("Multiplication result: " + product); 
            return "{\"factor1\":\"" + factor1 + "\", \"factor2\":\"" + factor2 + "\", \"product\": \"" + product + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid input: " + e.getMessage()); 
            return "{\"error\": \"Invalid input. Please provide valid integers.\"}"; 
        } catch (Exception e) { 
            logger.severe("Error during multiplication: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\": \"An error occurred during multiplication.\"}"; 
        } 
    } 
}
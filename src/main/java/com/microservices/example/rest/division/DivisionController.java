package com.microservices.example.rest.division; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class DivisionController { 
    protected Logger logger = Logger.getLogger(DivisionController.class.getName()); 
    @RequestMapping("/divide") 
    public String doDivide(@RequestParam(defaultValue="1") String dividend, 
                           @RequestParam(defaultValue="1") String divisor) { 
        try { 
            double div = Double.valueOf(dividend) / Double.valueOf(divisor); 
            logger.info("Division result is " + div); 
            return "{\"dividend\":\"" + dividend + "\", \"divisor\":\"" + divisor + "\", \"result\": \"" + div + "\"}"; 
        } catch (ArithmeticException e) { 
            logger.severe("Error during division: " + e.getMessage()); 
            return "{\"error\":\"Division by zero is not allowed\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid number format: " + e.getMessage()); 
            return "{\"error\":\"Invalid input format\"}"; 
        } catch (Exception e) { 
            logger.severe("Unexpected error: " + e.getMessage()); 
            return "{\"error\":\"An unexpected error occurred\"}"; 
        } 
    } 
}
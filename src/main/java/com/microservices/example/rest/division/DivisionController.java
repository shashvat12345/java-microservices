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
            if (Double.parseDouble(divisor) == 0) { 
                logger.warning("Attempt to divide by zero"); 
                return "{\"error\": \"Division by zero is not allowed.\"}"; 
            } 
            double result = Double.parseDouble(dividend) / Double.parseDouble(divisor); 
            logger.info("Division result is " + result); 
            return "{\"dividend\":\"" + dividend + "\", \"divisor\":\"" + divisor + "\", \"result\": \"" + result + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid number format: " + e.getMessage()); 
            return "{\"error\": \"Invalid number format.\"}"; 
        } catch (Exception e) { 
            logger.severe("An error occurred: " + e.getMessage()); 
            return "{\"error\": \"An unexpected error occurred.\"}"; 
        } 
    } 
}
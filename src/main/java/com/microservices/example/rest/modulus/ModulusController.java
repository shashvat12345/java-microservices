package com.microservices.example.rest.modulus; 
import java.util.logging.Logger; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class ModulusController { 
    protected Logger logger = Logger.getLogger(ModulusController.class.getName()); 
    @Autowired 
    private ModulusService modulusService; 
    @RequestMapping("/modulus") 
    public String doModulus(@RequestParam(defaultValue="0") String dividend, 
                             @RequestParam(defaultValue="1") String divisor) { 
        try { 
            int div = Integer.valueOf(dividend); 
            int divs = Integer.valueOf(divisor); 
            int result = modulusService.calculateModulus(div, divs); 
            logger.info("Modulus of " + div + " and " + divs + " is " + result); 
            return "{\"dividend\":\"" + dividend + "\", \"divisor\":\"" + divisor + "\", \"modulus\": \"" + result + "\"}"; 
        } catch (IllegalArgumentException e) { 
            logger.severe("Error calculating modulus: " + e.getMessage()); 
            return "{\"error\":\"" + e.getMessage() + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid number format: " + e.getMessage()); 
            return "{\"error\":\"Invalid number format\"}"; 
        } catch (Exception e) { 
            logger.severe("Unexpected error: " + e.getMessage()); 
            e.printStackTrace(); // Log the full stack trace for unexpected errors 
            return "{\"error\":\"Unexpected error occurred\"}"; 
        } 
    } 
}
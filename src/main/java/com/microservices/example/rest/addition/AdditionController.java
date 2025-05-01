package com.microservices.example.rest.addition; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class AdditionController { 
    protected Logger logger = Logger.getLogger(AdditionController.class.getName()); 
    @RequestMapping("/add") 
    public String doAdd(@RequestParam(defaultValue = "0") String addend1, 
                        @RequestParam(defaultValue = "0") String addend2) { 
        double augend1; 
        double augend2; 
        try { 
            // Attempt to parse both addends as doubles 
            augend1 = Double.parseDouble(addend1); 
            augend2 = Double.parseDouble(addend2); 
        } catch (NumberFormatException e) { 
            logger.severe("Invalid input: " + e.getMessage()); 
            return "{\"error\":\"Invalid input, please provide valid numbers or strings that can be converted to numbers.\"}"; 
        } 
        double sum = augend1 + augend2; 
        logger.info("Addition is " + sum); 
        return "{\"addend1\":\"" + addend1 + "\", \"addend2\":\"" + addend2 + "\", \"sum\": \"" + sum + "\"}"; 
    } 
}
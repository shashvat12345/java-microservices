package com.microservices.example.rest.addition; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
//REST API Controller class 
@RestController 
public class AdditionController { 
    protected Logger logger = Logger.getLogger(AdditionController.class.getName()); 
    //The request add will be mapped to a method doAdd 
    @RequestMapping("/add") 
    public String doAdd(@RequestParam(defaultValue="0") String addend1, 
                        @RequestParam(defaultValue="0") String addend2) { 
        try { 
            // Input validation 
            if (!isNumeric(addend1) || !isNumeric(addend2)) { 
                logger.severe("Invalid input: " + addend1 + ", " + addend2); 
                return "{\"error\":\"Invalid input. Please provide valid integers.\"}"; 
            } 
            int augend1 = Integer.valueOf(addend1); 
            int augend2 = Integer.valueOf(addend2); 
            int sum = augend1 + augend2; // Corrected the operation to addition 
            logger.info("Addition of " + augend1 + " and " + augend2 + " is " + sum); // Log the correct sum 
            return "{\"addend1\":\"" + addend1 + "\", \"addend2\":\"" + addend2 + "\", \"sum\": \"" + sum + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Error parsing numbers: " + e.getMessage()); 
            return "{\"error\":\"Invalid input. Please provide valid integers.\"}"; 
        } catch (Exception e) { 
            logger.severe("An unexpected error occurred: " + e.getMessage()); 
            return "{\"error\":\"An unexpected error occurred.\"}"; 
        } 
    } 
    // Helper method to check if a string is numeric 
    private boolean isNumeric(String str) { 
        return str != null && str.matches("-?\\d+(\\.\\d+)?"); 
    } 
}
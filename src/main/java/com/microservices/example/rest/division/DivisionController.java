package com.microservices.example.rest.division; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class DivisionController { 
    protected Logger logger = Logger.getLogger(DivisionController.class.getName()); 
    @RequestMapping("/divide") 
    public String doDivide(@RequestParam(defaultValue = "0") String dividend, 
                           @RequestParam(defaultValue = "1") String divisor) { 
        double num1 = Double.valueOf(dividend); 
        double num2 = Double.valueOf(divisor); 
        if (num2 == 0) { 
            logger.warning("Division by zero attempted"); 
            return "{\"error\": \"Division by zero is not allowed\"}"; 
        } 
        double quotient = num1 / num2; 
        logger.info("Division result: " + quotient); 
        return "{\"dividend\":\"" + dividend + "\", \"divisor\":\"" + divisor + "\", \"quotient\": \"" + quotient + "\"}"; 
    } 
}
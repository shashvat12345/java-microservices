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
        double num1 = Double.valueOf(dividend); 
        double num2 = Double.valueOf(divisor); 
        String result; 
        if (num2 == 0) { 
            result = "{\"error\": \"Division by zero is not allowed.\"}"; 
            logger.warning("Attempted division by zero."); 
        } else { 
            double quotient = num1 / num2; 
            result = "{\"dividend\":\"" + dividend + "\", \"divisor\":\"" + divisor + "\", \"quotient\": \"" + quotient + "\"}"; 
            logger.info("Division result: " + quotient); 
        } 
        return result; 
    } 
}
package com.microservices.example.rest.addition; 
import com.microservices.example.util.LoggingUtil; 
import com.microservices.example.web.LogService; // Add this import 
import org.springframework.beans.factory.annotation.Autowired; // Add this import 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
//REST API Controller class 
@RestController 
public class AdditionController { 
    @Autowired 
    private LogService logService; // Add this line 
    //The request add will be mapped to a method doAdd 
    @RequestMapping("/add") 
    public String doAdd(@RequestParam(defaultValue="0") String addend1, 
                        @RequestParam(defaultValue="0") String addend2) { 
        try { 
            int augend1 = Integer.valueOf(addend1); 
            int augend2 = Integer.valueOf(addend2); 
            int sum = augend1 + augend2; // Fixed operation from multiplication to addition 
            LoggingUtil.logInfo("Addition performed: " + sum); 
            return "{\"addend1\":\"" + addend1 + "\", \"addend2\":\"" + addend2 + "\", \"sum\": \"" + sum + "\"}"; 
        } catch (NumberFormatException e) { 
            LoggingUtil.logError("Invalid input for addition: addend1=" + addend1 + ", addend2=" + addend2, e); 
            logService.addLog("Invalid input for addition: addend1=" + addend1 + ", addend2=" + addend2); // Log the error 
            return "{\"error\":\"Invalid input. Please provide valid integers.\"}"; 
        } catch (Exception e) { 
            LoggingUtil.logError("An unexpected error occurred during addition: addend1=" + addend1 + ", addend2=" + addend2, e); 
            logService.addLog("An unexpected error occurred during addition: addend1=" + addend1 + ", addend2=" + addend2); 
            return "{\"error\":\"An unexpected error occurred. Please try again later.\"}"; 
        } 
    } 
}
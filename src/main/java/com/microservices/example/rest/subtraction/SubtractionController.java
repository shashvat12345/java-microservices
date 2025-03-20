package com.microservices.example.rest.subtraction; 
import com.microservices.example.util.LoggingUtil; 
import com.microservices.example.web.LogService; // Add this import 
import org.springframework.beans.factory.annotation.Autowired; // Add this import 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class SubtractionController { 
    @Autowired 
    private LogService logService; // Add this line 
    @RequestMapping("/subtract") 
    public String doSubtract(@RequestParam(defaultValue="0") String minuend, 
                             @RequestParam(defaultValue="0") String subtrahend) { 
        try { 
            int m = Integer.valueOf(minuend); 
            int s = Integer.valueOf(subtrahend); 
            int difference = m - s; 
            LoggingUtil.logInfo("Subtraction performed: " + difference); 
            return "{\"minuend\":\"" + minuend + "\", \"subtrahend\":\"" + subtrahend + "\", \"difference\": \"" + difference + "\"}"; 
        } catch (NumberFormatException e) { 
            LoggingUtil.logError("Invalid input for subtraction: minuend=" + minuend + ", subtrahend=" + subtrahend, e); 
            logService.addLog("Invalid input for subtraction: minuend=" + minuend + ", subtrahend=" + subtrahend); // Log the error 
            return "{\"error\":\"Invalid input. Please provide valid integers.\"}"; 
        } catch (Exception e) { 
            LoggingUtil.logError("An unexpected error occurred during subtraction: minuend=" + minuend + ", subtrahend=" + subtrahend, e); 
            logService.addLog("An unexpected error occurred during subtraction: minuend=" + minuend + ", subtrahend=" + subtrahend); 
            return "{\"error\":\"An unexpected error occurred. Please try again later.\"}"; 
        } 
    } 
}
package com.microservices.example.rest.trigonometry; 
import java.util.logging.Logger; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 
@RestController 
public class TrigonometryController { 
    protected Logger logger = Logger.getLogger(TrigonometryController.class.getName()); 
    @RequestMapping("/sine") 
    public String calculateSine(@RequestParam(defaultValue="0") String angle) { 
        try { 
            double radians = Math.toRadians(Double.parseDouble(angle)); 
            double result = Math.sin(radians); 
            logger.info("Sine of " + angle + " is " + result); 
            return "{\"angle\":\"" + angle + "\", \"sine\": \"" + result + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Error calculating sine: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"Invalid angle format\"}"; 
        } catch (Exception e) { 
            logger.severe("Unexpected error calculating sine: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An unexpected error occurred\"}"; 
        } 
    } 
    @RequestMapping("/cosine") 
    public String calculateCosine(@RequestParam(defaultValue="0") String angle) { 
        try { 
            double radians = Math.toRadians(Double.parseDouble(angle)); 
            double result = Math.cos(radians); 
            logger.info("Cosine of " + angle + " is " + result); 
            return "{\"angle\":\"" + angle + "\", \"cosine\": \"" + result + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Error calculating cosine: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"Invalid angle format\"}"; 
        } catch (Exception e) { 
            logger.severe("Unexpected error calculating cosine: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An unexpected error occurred\"}"; 
        } 
    } 
    @RequestMapping("/tangent") 
    public String calculateTangent(@RequestParam(defaultValue="0") String angle) { 
        try { 
            double radians = Math.toRadians(Double.parseDouble(angle)); 
            double result = Math.tan(radians); 
            logger.info("Tangent of " + angle + " is " + result); 
            return "{\"angle\":\"" + angle + "\", \"tangent\": \"" + result + "\"}"; 
        } catch (NumberFormatException e) { 
            logger.severe("Error calculating tangent: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"Invalid angle format\"}"; 
        } catch (Exception e) { 
            logger.severe("Unexpected error calculating tangent: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An unexpected error occurred\"}"; 
        } 
    } 
}
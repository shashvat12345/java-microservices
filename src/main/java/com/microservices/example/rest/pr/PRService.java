package com.microservices.example.rest.pr; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import com.microservices.example.web.WebAdditionService; 
import com.microservices.example.web.WebSubtractionService; 
import java.util.logging.Logger; 
@Service 
public class PRService { 
    protected Logger logger = Logger.getLogger(PRService.class.getName()); 
    @Autowired 
    private WebAdditionService additionService; 
    @Autowired 
    private WebSubtractionService subtractionService; 
    public String processRequest(String operation, String num1, String num2) { 
        String result; 
        try { 
            if ("add".equalsIgnoreCase(operation)) { 
                result = additionService.add(num1, num2); 
            } else if ("subtract".equalsIgnoreCase(operation)) { 
                result = subtractionService.subtract(num1, num2); 
            } else { 
                logger.severe("Invalid operation: " + operation); 
                return "{\"error\":\"Invalid operation\"}"; 
            } 
            logger.info("Operation: " + operation + ", Result: " + result); 
            return result; 
        } catch (Exception e) { 
            logger.severe("An error occurred while processing the request: " + e.getMessage()); 
            logger.severe("Error trace: " + e.getStackTrace()); 
            return "{\"error\":\"An unexpected error occurred\"}"; 
        } 
    } 
} 
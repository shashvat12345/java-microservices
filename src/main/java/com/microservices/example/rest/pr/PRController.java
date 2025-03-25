package com.microservices.example.rest.pr; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.logging.Logger; 
@Controller 
public class PRController { 
    protected Logger logger = Logger.getLogger(PRController.class.getName()); 
    @Autowired 
    private PRService prService; 
    @RequestMapping("/submitRequest") 
    public String submitRequest(@RequestParam String operation, 
                                @RequestParam String num1, 
                                @RequestParam String num2, 
                                Model model) { 
        String result; 
        try { 
            result = prService.processRequest(operation, num1, num2); 
            model.addAttribute("json", result); 
            logger.info("Operation: " + operation + ", Result: " + result); 
            return "result"; // Return to result page 
        } catch (Exception e) { 
            logger.severe("An error occurred while processing the request: " + e.getMessage()); 
            logger.severe("Error trace: " + e.getStackTrace()); 
            return "error"; // Handle unexpected errors 
        } 
    } 
}
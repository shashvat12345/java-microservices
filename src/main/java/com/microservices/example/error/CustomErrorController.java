package com.microservices.example.error; 
import org.springframework.boot.web.servlet.error.ErrorController; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
@Controller 
public class CustomErrorController implements ErrorController { 
    @RequestMapping("/error") 
    public String handleError(Model model) { 
        model.addAttribute("errorMessage", "Invalid or missing CSRF token. Please try again."); 
        return "error"; // Return to the error.html template 
    } 
    @Override 
    public String getErrorPath() { 
        return "/error"; 
    } 
} 
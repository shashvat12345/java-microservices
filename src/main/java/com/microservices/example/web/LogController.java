package com.microservices.example.web; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
@Controller 
public class LogController { 
    @Autowired 
    private LogService logService; 
    private Logger logger = Logger.getLogger(LogController.class.getName()); 
    @RequestMapping("/logs") 
    public String showLogs(Model model) { 
        logger.log(Level.INFO, "Displaying logs to the user."); 
        try { 
            model.addAttribute("logs", logService.getLogs()); 
        } catch (Exception e) { 
            logger.log(Level.SEVERE, "Error fetching logs: ", e); 
            model.addAttribute("logs", "Error fetching logs. Please try again later."); 
        } 
        return "logs"; // Return the name of the logs.html template 
    } 
}
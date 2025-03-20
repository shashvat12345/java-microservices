package com.microservices.example.web; 
import org.springframework.stereotype.Service; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
@Service 
public class LogService { 
    private List<String> logs = new ArrayList<>(); 
    private Logger logger = Logger.getLogger(LogService.class.getName()); 
    public void addLog(String log) { 
        logs.add(log); 
        logger.log(Level.INFO, "Log added: {0}", log); 
    } 
    public List<String> getLogs() { 
        logger.log(Level.INFO, "Fetching logs, total count: {0}", logs.size()); 
        return logs; 
    } 
} 
package com.microservices.example.util; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
public class LoggingUtil { 
    private static final Logger logger = Logger.getLogger(LoggingUtil.class.getName()); 
    public static void logError(String message, Exception e) { 
        logger.log(Level.SEVERE, message, e); 
    } 
    public static void logInfo(String message) { 
        logger.log(Level.INFO, message); 
    } 
}
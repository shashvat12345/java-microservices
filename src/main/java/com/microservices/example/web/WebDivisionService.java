package com.microservices.example.web; 
import java.util.logging.Logger; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.cloud.client.loadbalancer.LoadBalanced; 
import org.springframework.stereotype.Service; 
import org.springframework.web.client.RestTemplate; 
@Service 
public class WebDivisionService { 
    @Autowired 
    @LoadBalanced 
    protected RestTemplate restTemplate; 
    protected String serviceUrl; 
    protected Logger logger = Logger.getLogger(WebDivisionService.class.getName()); 
    public WebDivisionService(String serviceUrl) { 
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl; 
    } 
    public String divide(String dividend, String divisor) { 
        try { 
            logger.info("Requesting division with dividend: " + dividend + " and divisor: " + divisor); 
            String response = restTemplate.getForObject(serviceUrl + "/divide?dividend={dividend}&divisor={divisor}", String.class, dividend, divisor); 
            logger.info("Received response: " + response); 
            return response; 
        } catch (Exception e) { 
            logger.severe("Error occurred while performing division: " + e.getMessage()); 
            logger.severe("Stack trace: " + e.getStackTrace()); 
            return "{\"error\":\"An error occurred while performing division\"}"; 
        } 
    } 
} 
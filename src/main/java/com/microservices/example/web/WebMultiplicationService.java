package com.microservices.example.web; 
import java.util.logging.Logger; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.cloud.client.loadbalancer.LoadBalanced; 
import org.springframework.stereotype.Service; 
import org.springframework.web.client.RestTemplate; 
@Service 
public class WebMultiplicationService { 
    @Autowired 
    @LoadBalanced 
    protected RestTemplate restTemplate; 
    protected String serviceUrl; 
    protected Logger logger = Logger.getLogger(WebMultiplicationService.class.getName()); 
    public WebMultiplicationService(String serviceUrl) { 
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl; 
    } 
    public String multiply(String factor1, String factor2) { 
        try { 
            String result = restTemplate.getForObject(serviceUrl + "/multiply?factor1={factor1}&factor2={factor2}", String.class, factor1, factor2); 
            logger.info("Multiplication request successful: " + result); 
            return result; 
        } catch (Exception e) { 
            logger.severe("Error during multiplication request: " + e.getMessage()); 
            e.printStackTrace(); // Log the full stack trace 
            return "{\"error\":\"An error occurred while processing the multiplication request.\"}"; 
        } 
    } 
} 
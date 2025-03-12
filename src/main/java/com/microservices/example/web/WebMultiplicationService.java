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
    public String multiply(String multiplicand, String multiplier) { 
        try { 
            String result = restTemplate.getForObject(serviceUrl + "/multiply?multiplicand={multiplicand}&multiplier={multiplier}", String.class, multiplicand, multiplier); 
            logger.info("Multiplication request successful: " + multiplicand + " * " + multiplier + " = " + result); 
            return result; 
        } catch (Exception e) { 
            logger.severe("Error during multiplication: " + e.getMessage()); 
            logger.severe("Stack Trace: " + java.util.Arrays.toString(e.getStackTrace())); 
            return "{\"error\":\"Error occurred during multiplication\"}"; 
        } 
    } 
}
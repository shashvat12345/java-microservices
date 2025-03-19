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
            String result = restTemplate.getForObject(serviceUrl + "/divide?dividend={dividend}&divisor={divisor}", String.class, dividend, divisor); 
            logger.info("Division request sent: dividend=" + dividend + ", divisor=" + divisor); 
            return result; 
        } catch (Exception e) { 
            logger.severe("Error during division request: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An error occurred while processing the division request\"}"; 
        } 
    } 
}
package com.microservices.example.web; 
import java.util.logging.Logger; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.cloud.client.loadbalancer.LoadBalanced; 
import org.springframework.stereotype.Service; 
import org.springframework.web.client.RestTemplate; 
@Service 
public class WebTrigonometryService { 
    @Autowired 
    @LoadBalanced 
    protected RestTemplate restTemplate; 
    protected String serviceUrl; 
    protected Logger logger = Logger.getLogger(WebTrigonometryService.class.getName()); 
    public WebTrigonometryService(String serviceUrl) { 
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl; 
    } 
    public String sine(String angle) { 
        try { 
            String response = restTemplate.getForObject(serviceUrl + "/sine?angle={angle}", String.class, angle); 
            logger.info("Sine calculation successful for angle: " + angle); 
            return response; 
        } catch (Exception e) { 
            logger.severe("Error calling sine service: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An error occurred while calculating sine\"}"; 
        } 
    } 
    public String cosine(String angle) { 
        try { 
            String response = restTemplate.getForObject(serviceUrl + "/cosine?angle={angle}", String.class, angle); 
            logger.info("Cosine calculation successful for angle: " + angle); 
            return response; 
        } catch (Exception e) { 
            logger.severe("Error calling cosine service: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An error occurred while calculating cosine\"}"; 
        } 
    } 
    public String tangent(String angle) { 
        try { 
            String response = restTemplate.getForObject(serviceUrl + "/tangent?angle={angle}", String.class, angle); 
            logger.info("Tangent calculation successful for angle: " + angle); 
            return response; 
        } catch (Exception e) { 
            logger.severe("Error calling tangent service: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            return "{\"error\":\"An error occurred while calculating tangent\"}"; 
        } 
    } 
}
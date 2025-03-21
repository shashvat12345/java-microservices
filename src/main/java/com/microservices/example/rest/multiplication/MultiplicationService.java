package com.microservices.example.rest.multiplication; 
import org.springframework.stereotype.Service; 
import java.util.logging.Logger; 
@Service 
public class MultiplicationService { 
    protected Logger logger = Logger.getLogger(MultiplicationService.class.getName()); 
    public int multiply(int factor1, int factor2) { 
        int product = factor1 * factor2; 
        logger.info("Multiplication performed: " + factor1 + " * " + factor2 + " = " + product); 
        return product; 
    } 
} 
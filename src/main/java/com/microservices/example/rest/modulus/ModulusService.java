package com.microservices.example.rest.modulus; 
import org.springframework.stereotype.Service; 
@Service 
public class ModulusService { 
    public int calculateModulus(int dividend, int divisor) { 
        if (divisor == 0) { 
            throw new IllegalArgumentException("Divisor cannot be zero."); 
        } 
        return dividend % divisor; 
    } 
} 
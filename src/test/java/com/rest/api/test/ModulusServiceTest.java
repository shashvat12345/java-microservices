package com.rest.api.test; 
import com.microservices.example.rest.modulus.ModulusService; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
public class ModulusServiceTest { 
    private final ModulusService modulusService = new ModulusService(); 
    @Test 
    public void testCalculateModulus() { 
        try { 
            assertEquals(1, modulusService.calculateModulus(10, 3)); 
            assertEquals(0, modulusService.calculateModulus(10, 2)); 
            assertThrows(IllegalArgumentException.class, () -> modulusService.calculateModulus(10, 0)); 
        } catch (Exception e) { 
            System.err.println("Error during modulus calculation: " + e.getMessage()); 
            e.printStackTrace(); // Log the full stack trace for unexpected errors 
        } 
    } 
} 
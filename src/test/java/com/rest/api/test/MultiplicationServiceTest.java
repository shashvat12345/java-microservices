package com.rest.api.test; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.web.servlet.MockMvc; 
@SpringBootTest 
@AutoConfigureMockMvc 
public class MultiplicationServiceTest { 
    @Autowired 
    private MockMvc mockMvc; 
    @BeforeEach 
    public void setup() { 
        // Any setup tasks can be done here if needed 
    } 
    @Test 
    public void testMultiply() throws Exception { 
        mockMvc.perform(get("/multiply") 
                .param("multiplicand", "5") 
                .param("multiplier", "10")) 
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.product").value("50")); 
    } 
    @Test 
    public void testMultiplyWithInvalidInput() throws Exception { 
        mockMvc.perform(get("/multiply") 
                .param("multiplicand", "five") 
                .param("multiplier", "ten")) 
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.error").value("Invalid input, please provide valid integers.")); 
    } 
} 
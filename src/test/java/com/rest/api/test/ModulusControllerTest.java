package com.rest.api.test; 
import com.microservices.example.rest.modulus.ModulusController; 
import com.microservices.example.rest.modulus.ModulusService; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test; 
import org.mockito.Mockito; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; 
import org.springframework.http.MediaType; 
import org.springframework.test.web.servlet.MockMvc; 
import static org.mockito.ArgumentMatchers.anyString; 
import static org.mockito.Mockito.when; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
@WebMvcTest(ModulusController.class) 
public class ModulusControllerTest { 
    @Autowired 
    private MockMvc mockMvc; 
    private ModulusService modulusService; 
    @BeforeEach 
    void setUp() { 
        modulusService = Mockito.mock(ModulusService.class); 
    } 
    @Test 
    public void testDoModulus() throws Exception { 
        when(modulusService.calculateModulus(10, 3)).thenReturn(1); 
        mockMvc.perform(get("/modulus") 
                .param("dividend", "10") 
                .param("divisor", "3") 
                .accept(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()) 
                .andExpect(content().json("{\"dividend\":\"10\", \"divisor\":\"3\", \"modulus\": \"1\"}")); 
    } 
    @Test 
    public void testDoModulusWithZeroDivisor() throws Exception { 
        when(modulusService.calculateModulus(10, 0)).thenThrow(new IllegalArgumentException("Divisor cannot be zero.")); 
        mockMvc.perform(get("/modulus") 
                .param("dividend", "10") 
                .param("divisor", "0") 
                .accept(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()) 
                .andExpect(content().json("{\"error\":\"Divisor cannot be zero.\"}")); 
    } 
    @Test 
    public void testDoModulusWithInvalidNumberFormat() throws Exception { 
        mockMvc.perform(get("/modulus") 
                .param("dividend", "ten") 
                .param("divisor", "three") 
                .accept(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()) 
                .andExpect(content().json("{\"error\":\"Invalid number format\"}")); 
    } 
} 
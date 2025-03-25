package com.rest.api.test; 
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.web.servlet.MockMvc; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
@SpringBootTest 
@AutoConfigureMockMvc 
public class WebArithmeticControllerIntegrationTest { 
    @Autowired 
    private MockMvc mockMvc; 
    @Test 
    public void testAddIntegration() throws Exception { 
        mockMvc.perform(get("/add") 
                .param("addend1", "10") 
                .param("addend2", "20")) 
                .andExpect(status().isOk()) 
                .andExpect(content().json("{\"addend1\":\"10\", \"addend2\":\"20\", \"sum\": \"30\"}")); 
    } 
} 
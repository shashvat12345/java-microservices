package com.rest.api.test; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; 
import org.springframework.test.web.servlet.MockMvc; 
import org.springframework.web.context.WebApplicationContext; 
@WebMvcTest 
public class WebAdditionTest { 
    @Autowired 
    private MockMvc mockMvc; 
    @Autowired 
    private WebApplicationContext webApplicationContext; 
    @BeforeEach 
    public void setup() { 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
    } 
    @Test 
    public void testAddition() throws Exception { 
        mockMvc.perform(get("/add?addend1=10&addend2=15")) 
                .andExpect(status().isOk()) 
                .andExpect(view().name("sum")) 
                .andExpect(model().attributeExists("json")) 
                .andExpect(model().attribute("json", "{\"addend1\":\"10\", \"addend2\":\"15\", \"sum\": \"25\"}")); 
    } 
} 
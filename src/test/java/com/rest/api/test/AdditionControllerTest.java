package com.rest.api.test; 
import com.microservices.example.rest.addition.AdditionController; 
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; 
import org.springframework.http.MediaType; 
import org.springframework.test.web.servlet.MockMvc; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
@WebMvcTest(AdditionController.class) 
public class AdditionControllerTest { 
    @Autowired 
    private MockMvc mockMvc; 
    @Test 
    public void testDoAdd_ValidInputs() throws Exception { 
        mockMvc.perform(get("/add") 
                .param("addend1", "10") 
                .param("addend2", "20") 
                .accept(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()) 
                .andExpect(content().json("{\"addend1\":\"10\", \"addend2\":\"20\", \"sum\": \"30\"}")); 
    } 
    @Test 
    public void testDoAdd_InvalidInputs() throws Exception { 
        mockMvc.perform(get("/add") 
                .param("addend1", "abc") 
                .param("addend2", "20") 
                .accept(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()) 
                .andExpect(content().json("{\"error\": \"Invalid input, please provide valid integers.\"}")); 
    } 
    @Test 
    public void testDoAdd_EmptyInputs() throws Exception { 
        mockMvc.perform(get("/add") 
                .param("addend1", "") 
                .param("addend2", "") 
                .accept(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()) 
                .andExpect(content().json("{\"addend1\":\"0\", \"addend2\":\"0\", \"sum\": \"0\"}")); 
    } 
} 
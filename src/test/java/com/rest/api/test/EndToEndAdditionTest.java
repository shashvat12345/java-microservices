package com.rest.api.test; 
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.boot.test.web.client.TestRestTemplate; 
import org.springframework.http.ResponseEntity; 
import static org.assertj.core.api.Assertions.assertThat; 
import java.util.logging.Logger; 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class EndToEndAdditionTest { 
    @Autowired 
    private TestRestTemplate restTemplate; 
    protected Logger logger = Logger.getLogger(EndToEndAdditionTest.class.getName()); 
    @Test 
    public void testAdditionFunctionality() { 
        // Given two numbers 
        String addend1 = "5"; 
        String addend2 = "10"; 
        // When a request is made to the addition endpoint 
        ResponseEntity<String> response = restTemplate.getForEntity("/add?addend1=" + addend1 + "&addend2=" + addend2, String.class); 
        // Then the response should contain the correct sum 
        logger.info("Response for addition: " + response.getBody()); 
        assertThat(response.getBody()).contains("\"sum\": 15"); 
    } 
    @Test 
    public void testAdditionWithNegativeNumbers() { 
        // Given two negative numbers 
        String addend1 = "-5"; 
        String addend2 = "-10"; 
        // When a request is made to the addition endpoint 
        ResponseEntity<String> response = restTemplate.getForEntity("/add?addend1=" + addend1 + "&addend2=" + addend2, String.class); 
        // Then the response should contain the correct sum 
        logger.info("Response for addition with negative numbers: " + response.getBody()); 
        assertThat(response.getBody()).contains("\"sum\": -15"); 
    } 
    @Test 
    public void testAdditionWithZero() { 
        // Given zero and another number 
        String addend1 = "0"; 
        String addend2 = "10"; 
        // When a request is made to the addition endpoint 
        ResponseEntity<String> response = restTemplate.getForEntity("/add?addend1=" + addend1 + "&addend2=" + addend2, String.class); 
        // Then the response should contain the correct sum 
        logger.info("Response for addition with zero: " + response.getBody()); 
        assertThat(response.getBody()).contains("\"sum\": 10"); 
    } 
} 
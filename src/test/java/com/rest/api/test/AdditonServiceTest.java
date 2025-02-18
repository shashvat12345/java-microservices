package com.rest.api.test; 
import org.junit.jupiter.api.Test; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.web.client.RestTemplate; 
import static org.junit.jupiter.api.Assertions.assertEquals; 
@SpringBootTest 
public class AdditionServiceTest { 
    private final String BASE_URL = "http://localhost:2222/add"; // URL for the Addition Service 
    @Test 
    public void testAdditionWithPositiveNumbers() { 
        String response = new RestTemplate().getForObject(BASE_URL + "?addend1=5&addend2=10", String.class); 
        assertEquals("{\"addend1\":\"5\", \"addend2\":\"10\", \"sum\": 15}", response); 
    } 
    @Test 
    public void testAdditionWithNegativeNumbers() { 
        String response = new RestTemplate().getForObject(BASE_URL + "?addend1=-5&addend2=-10", String.class); 
        assertEquals("{\"addend1\":\"-5\", \"addend2\":\"-10\", \"sum\": -15}", response); 
    } 
    @Test 
    public void testAdditionWithZero() { 
        String response = new RestTemplate().getForObject(BASE_URL + "?addend1=0&addend2=10", String.class); 
        assertEquals("{\"addend1\":\"0\", \"addend2\":\"10\", \"sum\": 10}", response); 
        response = new RestTemplate().getForObject(BASE_URL + "?addend1=0&addend2=0", String.class); 
        assertEquals("{\"addend1\":\"0\", \"addend2\":\"0\", \"sum\": 0}", response); 
    } 
    @Test 
    public void testAdditionWithLargeNumbers() { 
        String response = new RestTemplate().getForObject(BASE_URL + "?addend1=1000000&addend2=2000000", String.class); 
        assertEquals("{\"addend1\":\"1000000\", \"addend2\":\"2000000\", \"sum\": 3000000}", response); 
    } 
    @Test 
    public void testAdditionWithInvalidInput() { 
        String response = new RestTemplate().getForObject(BASE_URL + "?addend1=abc&addend2=10", String.class); 
        assertEquals("{\"error\":\"Invalid input, please provide valid integers.\"}", response); 
    } 
} 
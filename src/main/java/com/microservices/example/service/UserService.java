package com.microservices.example.service; 
import com.microservices.example.model.User; 
public interface UserService { 
    User findUserByUsername(String username); 
} 
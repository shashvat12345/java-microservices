package com.microservices.example.service; 
import com.microservices.example.model.User; 
import org.springframework.stereotype.Service; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.logging.Logger; 
@Service 
public class UserServiceImpl implements UserService { 
    private final Map<String, User> users = new HashMap<>(); 
    protected Logger logger = Logger.getLogger(UserServiceImpl.class.getName()); 
    public UserServiceImpl() { 
        // Adding a default user for demonstration purposes 
        users.put("user", new User("user", "{noop}password", "USER")); // {noop} indicates no password encoder 
    } 
    @Override 
    public User findUserByUsername(String username) { 
        logger.info("Fetching user by username: " + username); 
        User user = users.get(username); 
        if (user == null) { 
            logger.warning("User not found: " + username); 
        } 
        return user; 
    } 
} 
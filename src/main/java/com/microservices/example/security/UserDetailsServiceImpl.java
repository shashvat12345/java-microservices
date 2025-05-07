package com.microservices.example.security; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service; 
import java.util.logging.Logger; 
@Service 
public class UserDetailsServiceImpl implements UserDetailsService { 
    protected Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName()); 
    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        logger.info("Attempting to load user by username: " + username); 
        // Here you would fetch user details from the database or other source 
        // For demonstration, we are using a hardcoded user 
        if ("user".equals(username)) { 
            logger.info("User found: " + username); 
            return org.springframework.security.core.userdetails.User.withUsername("user") 
                    .password("{noop}password") // {noop} indicates no password encoder 
                    .roles("USER") 
                    .build(); 
        } else { 
            logger.warning("User not found: " + username); 
            throw new UsernameNotFoundException("User not found"); 
        } 
    } 
}
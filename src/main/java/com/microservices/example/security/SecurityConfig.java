package com.microservices.example.security; 
import com.microservices.example.service.UserService; // Add the following import at the top 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.password.NoOpPasswordEncoder; 
import java.util.logging.Logger; 
@Configuration 
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
    protected Logger logger = Logger.getLogger(SecurityConfig.class.getName()); 
    @Autowired 
    private UserService userService; // Add @Autowired for UserService 
    @Override 
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
        logger.info("Configuring authentication manager with user details service."); 
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance()); // Update the configure method to use userService 
    } 
    @Override 
    protected void configure(HttpSecurity http) throws Exception { 
        logger.info("Configuring HTTP security."); 
        http 
            .authorizeRequests() 
                .antMatchers("/", "/login", "/webjars/**", "/css/**", "/js/**").permitAll() // Allow access to these endpoints 
                .antMatchers("/add", "/subtract").authenticated() // Protect arithmetic service endpoints 
                .anyRequest().authenticated() // All other requests need authentication 
                .and() 
            .formLogin() 
                .loginPage("/login") // Custom login page 
                .permitAll() 
                .and() 
            .logout() 
                .permitAll(); 
    } 
    @Bean 
    public NoOpPasswordEncoder passwordEncoder() { 
        logger.info("Creating NoOpPasswordEncoder bean."); 
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); 
    } 
}
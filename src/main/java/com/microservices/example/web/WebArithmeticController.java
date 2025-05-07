package com.microservices.example.web; 
import java.util.logging.Logger; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.web.csrf.CsrfToken; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.SessionAttributes; 
@Controller 
@SessionAttributes("csrfToken") 
public class WebArithmeticController { 
    @Autowired 
    protected WebAdditionService additionService; 
    @Autowired 
    protected WebSubtractionService subtractionService; 
    protected Logger logger = Logger.getLogger(WebArithmeticController.class.getName()); 
    public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService) { 
        this.additionService = additionService; 
        this.subtractionService = subtractionService; 
    } 
    @PostMapping("/add") 
    public String doAdd(@RequestParam(defaultValue = "0") String addend1, 
                        @RequestParam(defaultValue = "0") String addend2, 
                        Model model, CsrfToken csrfToken) { // CSRF Token validation 
        if (!csrfToken.getToken().equals(model.getAttribute("_csrf.token"))) { 
            logger.warning("CSRF token validation failed for addition."); 
            return "redirect:/error"; // Redirect to custom error page 
        } 
        String sum = additionService.add(addend1, addend2); 
        logger.info("Sum: " + sum); 
        model.addAttribute("json", sum); 
        return "sum"; 
    } 
    @PostMapping("/subtract") 
    public String doSubtract(@RequestParam(defaultValue = "0") String minuend, 
                             @RequestParam(defaultValue = "0") String subtrahend, 
                             Model model, CsrfToken csrfToken) { // CSRF Token validation 
        if (!csrfToken.getToken().equals(model.getAttribute("_csrf.token"))) { 
            logger.warning("CSRF token validation failed for subtraction."); 
            return "redirect:/error"; // Redirect to custom error page 
        } 
        String difference = subtractionService.subtract(minuend, subtrahend); 
        logger.info("Difference: " + difference); 
        model.addAttribute("json", difference); 
        return "difference"; 
    } 
}
package com.microservices.example.web; 
import java.util.logging.Logger; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
@Controller 
public class WebArithmeticController { 
    @Autowired 
    protected WebAdditionService additionService; 
    @Autowired 
    protected WebSubtractionService subtractionService; 
    @Autowired 
    protected WebMultiplicationService multiplicationService; // Add this line to include the multiplication service 
    protected Logger logger = Logger.getLogger(WebArithmeticController.class.getName()); 
    public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService, WebMultiplicationService multiplicationService) { 
        this.additionService = additionService; 
        this.subtractionService = subtractionService; 
        this.multiplicationService = multiplicationService; // Initialize multiplication service 
    } 
    @RequestMapping("/add") 
    public String doAdd(@RequestParam(defaultValue="0") String addend1, 
                        @RequestParam(defaultValue="0") String addend2, 
                        Model model) { 
        String sum = additionService.add(addend1, addend2); 
        logger.info("Sum: " + sum); 
        model.addAttribute("json", sum); 
        return "sum"; 
    } 
    @RequestMapping("/subtract") 
    public String doSubtract(@RequestParam(defaultValue="0") String minuend, 
                             @RequestParam(defaultValue="0") String subtrahend, 
                             Model model) { 
        String difference = subtractionService.subtract(minuend, subtrahend); 
        logger.info("Difference: " + difference); 
        model.addAttribute("json", difference); 
        return "difference"; 
    } 
    @RequestMapping("/multiply") 
    public String doMultiply(@RequestParam(defaultValue = "0") String factor1, 
                             @RequestParam(defaultValue = "0") String factor2, 
                             Model model) { 
        String product = multiplicationService.multiply(factor1, factor2); 
        logger.info("Product: " + product); 
        model.addAttribute("json", product); 
        return "multiply"; // Return the multiply.html template. 
    } 
}
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
    protected WebDivisionService divisionService; 
    @Autowired 
    protected WebMultiplicationService multiplicationService; 
    protected Logger logger = Logger.getLogger(WebArithmeticController.class.getName()); 
    public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService, WebDivisionService divisionService) { 
        this.additionService = additionService; 
        this.subtractionService = subtractionService; 
        this.divisionService = divisionService; 
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
    @RequestMapping("/divide") 
    public String doDivide(@RequestParam(defaultValue="0") String dividend, 
                           @RequestParam(defaultValue="1") String divisor, 
                           Model model) { 
        try { 
            String quotient = divisionService.divide(dividend, divisor); 
            logger.info("Quotient: " + quotient); 
            model.addAttribute("json", quotient); 
        } catch (Exception e) { 
            logger.severe("Error during division: " + e.getMessage()); 
            logger.severe("Stack trace: " + e.getStackTrace()); 
            model.addAttribute("json", "{\"error\":\"An error occurred during division.\"}"); 
        } 
        return "divide"; 
    } 
    @RequestMapping("/multiply") 
    public String doMultiply(@RequestParam(defaultValue="0") String factor1, 
                             @RequestParam(defaultValue="0") String factor2, 
                             Model model) { 
        try { 
            String product = multiplicationService.multiply(factor1, factor2); 
            logger.info("Product: " + product); 
            model.addAttribute("json", product); 
        } catch (Exception e) { 
            logger.severe("Error during multiplication: " + e.getMessage()); 
            logger.severe("Stack trace: " + e.getStackTrace()); 
            model.addAttribute("json", "{\"error\":\"An error occurred during multiplication.\"}"); 
        } 
        return "product"; 
    } 
}
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
    protected WebTrigonometryService trigonometryService; 
    @Autowired 
    protected WebMultiplicationService multiplicationService; 
    protected Logger logger = Logger.getLogger(WebArithmeticController.class.getName()); 
    public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService, WebDivisionService divisionService, WebTrigonometryService trigonometryService, WebMultiplicationService multiplicationService) { 
        this.additionService = additionService; 
        this.subtractionService = subtractionService; 
        this.divisionService = divisionService; 
        this.trigonometryService = trigonometryService; 
        this.multiplicationService = multiplicationService; 
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
    public String doDivide(@RequestParam(defaultValue="1") String dividend, 
                           @RequestParam(defaultValue="1") String divisor, 
                           Model model) { 
        try { 
            String result = divisionService.divide(dividend, divisor); 
            logger.info("Division result: " + result); 
            model.addAttribute("json", result); 
            return "divisionResult"; // Ensure the Thymeleaf template for division results exists 
        } catch (Exception e) { 
            logger.severe("Error during division: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            model.addAttribute("json", "{\"error\":\"An error occurred during division\"}"); 
            return "divisionResult"; // Return the same template with error message 
        } 
    } 
    @RequestMapping("/multiply") 
    public String doMultiply(@RequestParam(defaultValue="1") String multiplicand, 
                             @RequestParam(defaultValue="1") String multiplier, 
                             Model model) { 
        String product = multiplicationService.multiply(multiplicand, multiplier); 
        logger.info("Product: " + product); 
        model.addAttribute("json", product); 
        return "multiplicationResult"; // Ensure the Thymeleaf template for multiplication results exists 
    } 
    @RequestMapping("/sine") 
    public String doSine(@RequestParam(defaultValue="0") String angle, Model model) { 
        try { 
            String result = trigonometryService.sine(angle); 
            logger.info("Sine result: " + result); 
            model.addAttribute("json", result); 
            return "sineResult"; // Ensure the Thymeleaf template for sine results exists 
        } catch (Exception e) { 
            logger.severe("Error during sine calculation: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            model.addAttribute("json", "{\"error\":\"An error occurred during sine calculation\"}"); 
            return "sineResult"; // Return the same template with error message 
        } 
    } 
    @RequestMapping("/cosine") 
    public String doCosine(@RequestParam(defaultValue="0") String angle, Model model) { 
        try { 
            String result = trigonometryService.cosine(angle); 
            logger.info("Cosine result: " + result); 
            model.addAttribute("json", result); 
            return "cosineResult"; // Ensure the Thymeleaf template for cosine results exists 
        } catch (Exception e) { 
            logger.severe("Error during cosine calculation: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            model.addAttribute("json", "{\"error\":\"An error occurred during cosine calculation\"}"); 
            return "cosineResult"; // Return the same template with error message 
        } 
    } 
    @RequestMapping("/tangent") 
    public String doTangent(@RequestParam(defaultValue="0") String angle, Model model) { 
        try { 
            String result = trigonometryService.tangent(angle); 
            logger.info("Tangent result: " + result); 
            model.addAttribute("json", result); 
            return "tangentResult"; // Ensure the Thymeleaf template for tangent results exists 
        } catch (Exception e) { 
            logger.severe("Error during tangent calculation: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            model.addAttribute("json", "{\"error\":\"An error occurred during tangent calculation\"}"); 
            return "tangentResult"; // Return the same template with error message 
        } 
    } 
}
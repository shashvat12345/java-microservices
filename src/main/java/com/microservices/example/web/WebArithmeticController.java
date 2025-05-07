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
	protected Logger logger = Logger.getLogger(WebArithmeticController.class 
			.getName()); 
	public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService) { 
		this.additionService = additionService; 
		this.subtractionService = subtractionService; 
	} 
	//Exposing the request mapping of '/add for the http://localhost:4444/add -> to be consumed by the addition form 
	@RequestMapping("/add") 
	public String doAdd(@RequestParam(defaultValue="0") String addend1, 
			@RequestParam(defaultValue="0") String addend2, 
			Model model) {//Form parameters are accepted as arguments. 
		String sum = additionService.add(addend1, addend2);//Triggering a call to the Addition Service add method. 
		logger.info("Sum: " + sum); 
		model.addAttribute("json", sum); 
		return "sum"; 
	} 
	//Exposing the request mapping of '/subtract for the http://localhost:4444/subtract -> to be consumed by the subtraction form 
	@RequestMapping("/subtract") 
	public String doSubtract(@RequestParam(defaultValue="0") String minuend, 
			@RequestParam(defaultValue="0") String subtrahend, 
			Model model) { 
		String difference = subtractionService.subtract(minuend, subtrahend); 
		logger.info("Difference: " + difference); 
		model.addAttribute("json", difference); 
		return "difference"; 
	} 
	// Exposing the request mapping of '/divide' for the http://localhost:4444/divide -> to be consumed by the division form 
	@RequestMapping("/divide") 
	public String doDivide(@RequestParam(defaultValue = "0") String dividend, 
			@RequestParam(defaultValue = "1") String divisor, 
			Model model) { 
		try { 
			// Validate inputs 
			if (dividend == null || divisor == null || dividend.isEmpty() || divisor.isEmpty()) { 
				throw new IllegalArgumentException("Inputs cannot be empty."); 
			} 
			String quotient = divisionService.divide(dividend, divisor); // Triggering a call to the Division Service divide method. 
			logger.info("Quotient: " + quotient); 
			model.addAttribute("json", quotient); 
			return "quotient"; // This should point to a new Thymeleaf template that will display the result. 
		} catch (IllegalArgumentException e) { 
			logger.severe("Invalid input: " + e.getMessage()); 
			model.addAttribute("error", "Invalid input: " + e.getMessage()); 
			return "error"; // Redirect to error page 
		} catch (Exception e) { 
			logger.severe("Error during division: " + e.getMessage()); 
			logger.severe("Stack Trace: " + e.getStackTrace()); 
			model.addAttribute("error", "An error occurred during division: " + e.getMessage()); 
			return "error"; // Redirect to error page 
		} 
	} 
}
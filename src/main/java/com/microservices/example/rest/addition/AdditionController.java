package com.microservices.example.rest.addition;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// REST API Controller class 
@RestController
public class AdditionController {

    protected Logger logger = Logger.getLogger(AdditionController.class.getName());

    // The request add will be mapped to a method doAdd 
    @GetMapping("/add")
    public String doAdd(@RequestParam(value = "addend1", defaultValue = "0") String addend1,
                        @RequestParam(value = "addend2", defaultValue = "0") String addend2) {

        int num1;
        int num2;
        try {
            num1 = Integer.parseInt(addend1);
        } catch (NumberFormatException e) {
            logger.warning("Invalid input for addend1: " + addend1);
            return "Invalid input for addend1. Please provide a valid integer.";
        }
        try {
            num2 = Integer.parseInt(addend2);
        } catch (NumberFormatException e) {
            logger.warning("Invalid input for addend2: " + addend2);
            return "Invalid input for addend2. Please provide a valid integer.";
        }

        int sum = calculate(num1, num2);
        logger.info("Addition is " + sum);
        return String.format("{\"addend1\": %d, \"addend2\": %d, \"sum\": %d}", num1, num2, sum);
    }

    private int calculate(int a, int b) {
        return a + b;
    }
}
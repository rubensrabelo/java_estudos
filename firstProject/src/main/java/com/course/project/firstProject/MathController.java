package com.course.project.firstProject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.course.project.firstProject.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(
				@PathVariable(value = "numberOne") String numberOne,
				@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please, insert a number.");
		}
		
		return convertToNumber(numberOne) + convertToNumber(numberTwo);
	} 

	private Double convertToNumber(String strNumber) {
		if(strNumber == null) return 0D;
		
		String number = strNumber.replace(",", ".");
		
		return Double.parseDouble(number);
	}

	private boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		
		String number = strNumber.replace(",", ".");
		
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}

package com.course.course;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.course.course.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(
				@PathVariable(value = "numberOne") String numberOne,
				@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
			
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtraction(
				@PathVariable(value = "numberOne") String numberOne,
				@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}")	
	public Double mutiplication(
				@PathVariable(value = "nummberOne") String numberOne,
				@PathVariable(value = "nummberTwo") String numberTwo
			) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/div/{numerator}/{denominator}")
	public Double division(
			@PathVariable(value = "numerator") String numerator,
			@PathVariable(value = "denominator") String denominator
			) throws Exception {
		
		if(!isNumeric(numerator) || !isNumeric(denominator)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		if(convertToDouble(denominator) == 0) {
			throw new UnsupportedMathOperationException("There is no division with the denominator equal to zero.");
		}
		
		return convertToDouble(numerator) / convertToDouble(denominator);	
	}
	
	@RequestMapping(value = "/average/{numberOne}/{numberTwo}")
	public Double average(
				@PathVariable(value = "numberOne") String numberOne,
				@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
	}
	
	@RequestMapping(value = "/square/{number}")
	public Double squareRoot(
				@PathVariable(value = "number") String number
			) throws Exception {
		
		if(!isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		if(convertToDouble(number) < 0) {
			throw new UnsupportedMathOperationException("There is no square root of a negative number in real numbers");
		}
		
		return Math.sqrt(convertToDouble(number));
	}

	private Double convertToDouble(String strNumber) {
		
		if(strNumber == null) return 0D;
		
		String number = strNumber.replaceAll(",", ".");
		
		if(isNumeric(number)) return Double.parseDouble(number);
		
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		
		String number = strNumber.replaceAll(",", ".");
		
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}

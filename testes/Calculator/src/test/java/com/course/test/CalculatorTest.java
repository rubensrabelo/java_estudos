package com.course.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void integerDivision() {
        Calculator calculator = new Calculator();
        int result = calculator.division(4, 2);

        assertEquals(2, result, "4/2 did not produce 2");
    }

    @Test
    void integerSubtraction() {
        Calculator calculator = new Calculator();
        int a = 4;
        int b = 2;
        int expectedResult = a - b;
        int actualResult = calculator.subtraction(a, b);
        assertEquals(expectedResult, actualResult,
                ()->a + " - " + b + " did not produce " + actualResult);
    }
}
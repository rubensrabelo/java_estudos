package com.course.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test math operations in calculator class")
class CalculatorTest {

    static Calculator calculator;

    @BeforeAll
    static void setup() {
        calculator = new Calculator();
    }

    @DisplayName("Test 4/2 = 2")
    @Test
    void testDivision_WhenFourIsDividesByTwo_ShouldReturnTwo() {
        int result = calculator.division(4, 2);

        assertEquals(2, result, "4/2 did not produce 2");
    }

    @Test
    void integerSubtraction() {
        int a = 4;
        int b = 2;
        int expectedResult = a - b;
        int actualResult = calculator.subtraction(a, b);
        assertEquals(expectedResult, actualResult,
                ()->a + " - " + b + " did not produce " + actualResult);
    }
}
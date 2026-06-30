package com.deepskilling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAdditionWithPositiveNumbers() {
        // Arrange
        int number1 = 2;
        int number2 = 3;
        int expectedSum = 5;

        // Act
        int actualSum = calculator.add(number1, number2);

        // Assert
        assertEquals(expectedSum, actualSum);
    }
}
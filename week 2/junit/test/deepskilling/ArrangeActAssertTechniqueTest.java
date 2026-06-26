package com.deepskilling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrangeActAssertTechniqueTest {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @AfterEach
    public void tearDown() {
        orderService = null;
    }

    @Test
    public void testCalculateTotalWithTax() {
        // Arrange
        double price = 20.0;
        int qty = 2;
        double tax = 0.10;
        double expectedTotal = 44.0;

        // Act
        double actualTotal = orderService.calculateTotalWithTax(price, qty, tax);

        // Assert
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testFreeShippingEligibility() {
        // Arrange
        double orderTotal = 75.50;

        // Act
        boolean actualResult = orderService.isEligibleForFreeShipping(orderTotal);

        // Assert
        assertTrue(actualResult);
    }
}
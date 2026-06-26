package com.deepskilling;

public class OrderService {

    public double calculateTotalWithTax(double itemPrice, int quantity, double taxRate) {
        if (itemPrice < 0 || quantity <= 0 || taxRate < 0) {
            return 0.0;
        }
        double subtotal = itemPrice * quantity;
        return subtotal + (subtotal * taxRate);
    }

    public boolean isEligibleForFreeShipping(double totalAmount) {
        return totalAmount >= 50.0;
    }
}
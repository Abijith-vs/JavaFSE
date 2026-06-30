package com.deepskilling;

public class BookService {

    public double calculateDiscount(double price, String customerType) {
        if ("VIP".equalsIgnoreCase(customerType)) {
            return price * 0.20;
        } else if ("Regular".equalsIgnoreCase(customerType)) {
            return price * 0.05;
        }
        return 0.0;
    }

    public boolean isValidIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }
        return isbn.length() == 10 || isbn.length() == 13;
    }

    public String getBookFormat(String fileExtension) {
        if (fileExtension == null) {
            return null;
        }
        if (".epub".equalsIgnoreCase(fileExtension) || ".mobi".equalsIgnoreCase(fileExtension)) {
            return "E-Book";
        }
        return "Unknown";
    }
}
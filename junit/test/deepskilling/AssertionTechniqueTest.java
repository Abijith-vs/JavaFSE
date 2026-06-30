package com.deepskilling;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionTechniqueTest {

    @Test
    public void testBookServiceBehaviors() {
        BookService service = new BookService();

        double vipDiscount = service.calculateDiscount(100.0, "VIP");
        assertEquals(20.0, vipDiscount);

        double regularDiscount = service.calculateDiscount(100.0, "Regular");
        assertNotEquals(20.0, regularDiscount);

        assertTrue(service.isValidIsbn("1234567890"));
        assertFalse(service.isValidIsbn("123"));

        assertNull(service.getBookFormat(null));
        assertNotNull(service.getBookFormat(".epub"));
        assertEquals("E-Book", service.getBookFormat(".epub"));
    }
}
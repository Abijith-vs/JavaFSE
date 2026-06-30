package com.deepskilling;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockAndStubTechniqueTest {

    @Test
    public void testMockingAndStubbing() {
        WeatherGateway mockGateway = Mockito.mock(WeatherGateway.class);
        when(mockGateway.getConditions("London")).thenReturn("Sunny");

        WeatherService service = new WeatherService(mockGateway);
        String finalResult = service.processForecast("London");

        assertEquals("City Status: Sunny", finalResult);
    }
}
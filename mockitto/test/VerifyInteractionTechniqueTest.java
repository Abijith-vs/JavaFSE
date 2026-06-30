package com.deepskilling;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class VerifyInteractionTechniqueTest {

    @Test
    public void testVerifyingInteractions() {
        WeatherGateway mockGateway = Mockito.mock(WeatherGateway.class);
        WeatherService service = new WeatherService(mockGateway);

        service.processForecast("Tokyo");

        verify(mockGateway).getConditions("Tokyo");
    }
}

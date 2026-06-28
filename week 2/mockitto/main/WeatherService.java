package com.deepskilling;

public class WeatherService {
    private final WeatherGateway gateway;

    public WeatherService(WeatherGateway gateway) {
        this.gateway = gateway;
    }

    public String processForecast(String city) {
        String data = gateway.getConditions(city);
        if (data == null) {
            return "Unavailable";
        }
        return "City Status: " + data;
    }
}
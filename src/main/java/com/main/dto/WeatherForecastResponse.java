package com.main.dto;

public class WeatherForecastResponse {
    private String detailedForecast;
    private String name;

    public WeatherForecastResponse(String detailedForecast, String name) {
        this.detailedForecast = detailedForecast;
        this.name = name;
    }

    public WeatherForecastResponse() {
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "\t\t\t\t" + detailedForecast;

    }
}

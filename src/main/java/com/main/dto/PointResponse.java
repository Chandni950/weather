package com.main.dto;

public class PointResponse {
    private String forecast;
    private RelativeLocation relativeLocation;
    private String radarStation;
    private String timeZone;


    public PointResponse(String forecast, RelativeLocation relativeLocation, String radarStation, String timeZone) {
        this.forecast = forecast;
        this.relativeLocation = relativeLocation;
        this.radarStation = radarStation;
        this.timeZone = timeZone;
    }

    public String getForecast() {
        return forecast;
    }

    public RelativeLocation getRelativeLocation() {
        return relativeLocation;
    }

    public String getRadarStation() {
        return radarStation;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return
                "City= " + relativeLocation.getProperties().getCity() +
                        "\nState= " + relativeLocation.getProperties().getState() +
                        "\nRadarStation= " + radarStation +
                        "\nTimeZone= " + timeZone;
    }
}

class Properties {
    private String city;
    private String state;

    public Properties(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

class RelativeLocation {
    private Properties properties;

    public RelativeLocation(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "RelativeLocation{" +
                "properties=" + properties +
                '}';
    }

    public Properties getProperties() {
        return properties;
    }


}
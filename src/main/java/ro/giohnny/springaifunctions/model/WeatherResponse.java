package ro.giohnny.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;


public record WeatherResponse(
        Location location,
        Current current
) {
    public record Location(
            String name,
            String region,
            String country,
            BigDecimal lat,
            BigDecimal lon,
            String tz_id,
            long localtime_epoch,
            String localtime
    ) {}

    public record Current(
            @JsonPropertyDescription("Current Temperature in Celsius") BigDecimal temp_c,
            @JsonPropertyDescription("WindSpeed in KMH") BigDecimal wind_kph,
            @JsonPropertyDescription("Direction of wind") String wind_dir,
            @JsonPropertyDescription("Current Humidity") int humidity,
            @JsonPropertyDescription("Cloud Coverage Percentage") int cloud,
            @JsonPropertyDescription("Feels like Temperature in Celsius") BigDecimal feelslike_c
    ) {}
}
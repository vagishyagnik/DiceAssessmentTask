package com.dice.tech.model;

import com.dice.tech.deserializers.WeatherDataDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = WeatherDataDeserializer.class)
public class WeatherData {
    private Long time;
    private Double currTemperature;
    private Double maxTemperature;
    private Double minTemperature;
    private Double humidity;
    private String weatherIcon;
    private String weatherDescription;
    private Double windSpeed;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getCurrTemperature() {
        return currTemperature;
    }

    public void setCurrTemperature(Double currTemperature) {
        this.currTemperature = currTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}

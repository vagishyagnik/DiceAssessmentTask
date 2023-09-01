package com.dice.tech.controllers;

import com.dice.tech.model.WeatherDataSummary;
import com.dice.tech.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/hourly-forecast")
    public ResponseEntity<Object> hourlyForecast(String city) {
        try {
            return ResponseEntity.ok(this.weatherService.getHourlyForecastByLocationName(city));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/forecast-summary")
    public ResponseEntity<WeatherDataSummary> forecastSummary(String city) {
        try {
            return ResponseEntity.ok(this.weatherService.getForecastSummaryByLocationName(city));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.ok(null);
        }
    }
}

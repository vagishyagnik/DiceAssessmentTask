package com.dice.tech.controllers;

import com.dice.tech.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
public class WeatherController {

    Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/hourly-forecast")
    public ResponseEntity<Object> hourlyForecast(String city) {
        try {
            if (city == null || city.isEmpty()) return ResponseEntity.ok("{}");
            return ResponseEntity.ok(this.weatherService.getHourlyForecastByLocationName(city));
        } catch (Exception e) {
            LOGGER.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/forecast-summary")
    public ResponseEntity<Object> forecastSummary(String city) {
        try {
            if (city == null) return ResponseEntity.ok("{}");
            return ResponseEntity.ok(this.weatherService.getForecastSummaryByLocationName(city));
        } catch (Exception e) {
            LOGGER.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().build();
        }
    }
}

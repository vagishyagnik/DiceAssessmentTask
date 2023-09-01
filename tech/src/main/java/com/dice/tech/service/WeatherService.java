package com.dice.tech.service;

import com.dice.tech.model.AccumulatedData;
import com.dice.tech.model.WeatherDataSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${openweather.apiKey}")
    private String apiKey;
    private final String hourlyForecastUrl;
    private final String forecastSummaryUrl;

    public WeatherService() {
        this.hourlyForecastUrl = "https://api.openweathermap.org/data/2.5/forecast?q={location}&appid={apiKey}";
        this.forecastSummaryUrl = "https://api.openweathermap.org/data/2.5/weather?q={location}&appid={apiKey}";
    }

    private String modifyUrl(String locationName, String url) {
        return url.replace("{location}", locationName)
                .replace("{apiKey}", this.apiKey);
    }

    public Object getHourlyForecastByLocationName(String location) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = modifyUrl(location, this.hourlyForecastUrl);
        String json = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        AccumulatedData data = objectMapper.readValue(json, AccumulatedData.class);
        System.out.println(data);
        return data;
    }

    public WeatherDataSummary getForecastSummaryByLocationName(String location) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = modifyUrl(location, this.forecastSummaryUrl);
        String json = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherDataSummary data = objectMapper.readValue(json, WeatherDataSummary.class);
        System.out.println(data);
        return data;
    }
}

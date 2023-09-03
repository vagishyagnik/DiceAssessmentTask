package com.dice.tech.deserializers;

import com.dice.tech.model.WeatherData;
import com.dice.tech.model.WeatherDataSummary;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class WeatherDataSummaryDeserializer extends StdDeserializer<WeatherDataSummary> {
    protected WeatherDataSummaryDeserializer() {
        super(WeatherData.class);
    }
    protected WeatherDataSummaryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WeatherDataSummary deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        WeatherDataSummary weatherData = new WeatherDataSummary();
        weatherData.setLocation(node.get("name").asText());
        weatherData.setLatitude(node.get("coord").get("lat").asText());
        weatherData.setLongitude(node.get("coord").get("lon").asText());
        weatherData.setCountry(node.get("sys").get("country").asText());
        weatherData.setTimezone(node.get("timezone").asLong());
        weatherData.setCurrTemperature(node.get("main").get("temp").asDouble());
        weatherData.setMaxTemperature(node.get("main").get("temp_max").asDouble());
        weatherData.setMinTemperature(node.get("main").get("temp_min").asDouble());
        weatherData.setFeelsLikeTemperature(node.get("main").get("feels_like").asDouble());
        weatherData.setHumidity(node.get("main").get("humidity").asLong());
        weatherData.setVisibility(node.get("visibility").asLong());
        weatherData.setWeatherIcon(node.get("weather").get(0).get("icon").asText());
        weatherData.setWeatherDescription(node.get("weather").get(0).get("main").asText());
        weatherData.setWindSpeed(node.get("wind").get("speed").asDouble());
        weatherData.setSunset(node.get("sys").get("sunrise").asLong());
        weatherData.setSunset(node.get("sys").get("sunset").asLong());

        return weatherData;
    }
}

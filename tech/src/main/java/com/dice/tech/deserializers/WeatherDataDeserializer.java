package com.dice.tech.deserializers;

import com.dice.tech.model.WeatherData;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class WeatherDataDeserializer extends StdDeserializer<WeatherData> {
    protected WeatherDataDeserializer() {
        super(WeatherData.class);
    }
    protected WeatherDataDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WeatherData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        WeatherData weatherData = new WeatherData();
        weatherData.setTime(node.get("dt").asLong());
        weatherData.setCurrTemperature(node.get("main").get("temp").asDouble());
        weatherData.setMaxTemperature(node.get("main").get("temp_max").asDouble());
        weatherData.setMinTemperature(node.get("main").get("temp_min").asDouble());
        weatherData.setHumidity(node.get("main").get("humidity").asDouble());
        weatherData.setWeatherIcon(node.get("weather").get(0).get("icon").asText());
        weatherData.setWeatherDescription(node.get("weather").get(0).get("main").asText());
        weatherData.setWindSpeed(node.get("wind").get("speed").asDouble());

        return weatherData;
    }
}

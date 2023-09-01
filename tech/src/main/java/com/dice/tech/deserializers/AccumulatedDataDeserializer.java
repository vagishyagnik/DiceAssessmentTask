package com.dice.tech.deserializers;

import com.dice.tech.model.AccumulatedData;
import com.dice.tech.model.WeatherData;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccumulatedDataDeserializer extends StdDeserializer<AccumulatedData> {

    protected AccumulatedDataDeserializer() {
        super(WeatherData.class);
    }
    protected AccumulatedDataDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AccumulatedData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        AccumulatedData accmData = new AccumulatedData();
        List<WeatherData> weatherData = new ArrayList<>();
        ArrayNode list = ((ArrayNode) node.get("list"));
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i=0; i<list.size(); i++) {
            WeatherData temp = objectMapper.readValue(objectMapper.writeValueAsString(list.get(i)), WeatherData.class);
            weatherData.add(temp);
        }

        accmData.setData(weatherData);
        accmData.setLocation(node.get("city").get("name").asText());
        accmData.setLatitude(node.get("city").get("coord").get("lat").asText());
        accmData.setLongitude(node.get("city").get("coord").get("lon").asText());
        accmData.setTimezone(node.get("city").get("timezone").asLong());
        accmData.setSunrise(node.get("city").get("sunrise").asLong());
        accmData.setSunset(node.get("city").get("sunset").asLong());
        accmData.setCountry(node.get("city").get("country").asText());

        return accmData;
    }
}

package pl.akademiaspring.temperature_update.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.akademiaspring.temperature_update.model.CityData;
import pl.akademiaspring.temperature_update.model.WeatherData;
import pl.akademiaspring.temperature_update.repo.CityTemperatureRepo;

import java.util.List;


@RestController
public class WeatherController {

    private RestTemplate restTemplate;

    @Autowired
    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int getWoeid(String city) {
        String url = "https://www.metaweather.com/api/location/search/?query=" + city;
        CityData [] cityData = restTemplate.getForObject(url, CityData[].class);
        return cityData[0].getWoeid();
    }

    public WeatherData getWeather(String city) {
        int woeid = this.getWoeid(city);
        List<WeatherData> weatherDataList = null;

            String url = "https://www.metaweather.com/api/location/" + woeid;
            JsonNode table = restTemplate.getForObject(url, JsonNode.class, new Object[0]).get("consolidated_weather");
            ObjectMapper mapper = new ObjectMapper();

            try {
                weatherDataList = mapper.readValue(table.toString(), new TypeReference<List<WeatherData>>() {
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        return weatherDataList.get(0);
    }
}

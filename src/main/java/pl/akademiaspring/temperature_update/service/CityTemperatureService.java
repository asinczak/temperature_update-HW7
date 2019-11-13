package pl.akademiaspring.temperature_update.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.akademiaspring.temperature_update.controller.WeatherController;
import pl.akademiaspring.temperature_update.model.CityTemperature;
import pl.akademiaspring.temperature_update.model.WeatherData;
import pl.akademiaspring.temperature_update.repo.CityTemperatureRepo;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@EnableScheduling
public class CityTemperatureService {

    @Value("${city.name.1}")
    private String city1;
    @Value("${city.name.2}")
    private String city2;

    private CityTemperatureRepo cityTemperatureRepo;
    private WeatherController weatherController;

    @Autowired
    public CityTemperatureService(CityTemperatureRepo cityTemperatureRepo, WeatherController weatherController) {
        this.cityTemperatureRepo = cityTemperatureRepo;
        this.weatherController = weatherController;
    }

    public CityTemperature getCityTemperature(String city){
        WeatherData weatherData = weatherController.getWeather(city);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String actualDate = formatter.format(date);
        return new CityTemperature(city, weatherData.getTheTemp(), actualDate);
    }

    @Scheduled(fixedDelay = 3600000)
    public void saveCityTemperature(){
        cityTemperatureRepo.save(getCityTemperature(city1));
        cityTemperatureRepo.save(getCityTemperature(city2));
    }
}

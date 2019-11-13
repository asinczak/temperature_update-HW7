package pl.akademiaspring.temperature_update.model;

import javax.persistence.*;

@Entity
@Table(name = "temperatures")
public class CityTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private Double temperature;
    private String date;

    public CityTemperature(String city, Double temperature, String date) {
        this.city = city;
        this.temperature = temperature;
        this.date = date;
    }

    public CityTemperature() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

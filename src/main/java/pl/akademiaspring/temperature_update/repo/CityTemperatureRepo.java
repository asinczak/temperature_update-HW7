package pl.akademiaspring.temperature_update.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspring.temperature_update.model.CityTemperature;

@Repository
public interface CityTemperatureRepo extends JpaRepository<CityTemperature, Long> {


}

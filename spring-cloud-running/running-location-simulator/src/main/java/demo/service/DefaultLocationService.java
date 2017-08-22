package demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.domain.GpsSimulatorRequest;
import demo.domain.SimulatorInitLocations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by XL on 8/23/2017.
 */
@Service
public class DefaultLocationService implements LocationService {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public SimulatorInitLocations loadLocationFixture() {
        InputStream is = this.getClass().getResourceAsStream("/init-location.json");
        try {
            return objectMapper.readValue(is, SimulatorInitLocations.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

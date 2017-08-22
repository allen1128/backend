package demo.domain;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XL on 8/22/2017.
 */
public class SimulatorInitLocations {
    private List<GpsSimulatorRequest> gpsSimulatorRequests = new ArrayList<GpsSimulatorRequest>(0);

    public int getNumberOfGpsSimulatorRequests() {
        return gpsSimulatorRequests.size();
    }

    public void setGpsSimulatorRequests(List<GpsSimulatorRequest> gpsSimulatorRequests) {
        Assert.notEmpty(gpsSimulatorRequests, "gpsSimulatorRequests must not be empty.");
        this.gpsSimulatorRequests = gpsSimulatorRequests;
    }
}

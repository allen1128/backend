package demo.service;

import demo.domain.GpsSimulatorRequest;
import demo.domain.Point;
import demo.task.LocationSimulator;

import java.util.List;

/**
 * Created by XL on 8/23/2017.
 */
public class DefaultGpsSimulatorFactory implements GpsSimulatorFactory {
    @Override
    public LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        return null;
    }

    @Override
    public LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points) {
        return null;
    }
}

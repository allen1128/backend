package demo.service;

import demo.domain.GpsSimulatorRequest;
import demo.domain.Point;
import demo.task.LocationSimulator;

import java.util.List;

/**
 * Created by XL on 8/21/2017.
 */
public interface GpsSimulatorFactory {
    LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest);
    LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points);
}

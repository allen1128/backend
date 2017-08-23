package demo.service;

import demo.domain.GpsSimulatorRequest;
import demo.domain.Leg;
import demo.domain.Point;
import demo.task.LocationSimulator;
import demo.util.NavUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by XL on 8/23/2017.
 */
public class DefaultGpsSimulatorFactory implements GpsSimulatorFactory {

    @Autowired
    PositionService positionService;
    private final AtomicLong instanceCounter = new AtomicLong();

    @Override
    public LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        final LocationSimulator locationSimulator = new LocationSimulator(gpsSimulatorRequest);
        locationSimulator.setPositionInfoService(positionService);
        locationSimulator.setId(this.instanceCounter.incrementAndGet());
        final List<Point> points = NavUtil.decodePolyline(gpsSimulatorRequest.getPolyline());
        locationSimulator.setStartPoint(points.iterator().next());
        return prepareGpsSimulator(locationSimulator, points);
    }

    @Override
    public LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points) {
        locationSimulator.setCurrentPosition(null);
        final List<Leg> legs = createLegList(points);
        locationSimulator.setLegs(legs);
        locationSimulator.setStartPosition();
        return locationSimulator;
    }

    private List<Leg> createLegList(List<Point> points) {
        final List<Leg> legs = new ArrayList<Leg>();
        for (int i = 0; i < points.size() - 1; i++){
            Leg leg = new Leg();
            leg.setId(i);
            leg.setStartPosition(points.get(i));;
            leg.setEndPosition(points.get(i+1));
            Double length = NavUtil.getDistance(points.get(i), points.get(i + 1));
            leg.setLength(length);
            Double heading = NavUtil.getBearing(points.get(i), points.get(i + 1));
            leg.setHeading(heading);
            legs.add(leg);
        }
        return legs;
    }
}

package demo.util;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import demo.domain.Point;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by XL on 8/23/2017.
 */
public class NavUtil {
    private static double EARTH_RADIUS_IN_METERS = DistanceUtils.EARTH_EQUATORIAL_RADIUS_KM * 1000;

    public static double getDistance(Point point1, Point point2) {
        Assert.notNull(point1, "point1 must not be null");
        Assert.notNull(point2, "point2 must not be null");

        final SpatialContext ctx = SpatialContext.GEO;
        com.spatial4j.core.shape.Point p1 = ctx.makePoint(point1.getLongitude(), point1.getLatitude());
        com.spatial4j.core.shape.Point p2 = ctx.makePoint(point2.getLongitude(), point2.getLatitude());

        return DistanceUtils
                .degrees2Dist(ctx.getDistCalc().distance(p1, p2), DistanceUtils.EARTH_MEAN_RADIUS_KM) * 1000;
    }

    public static double getTotalDistance(List<Point> points) {
        double totalDistance = 0.0;
        Point prev = points.iterator().next();
        int count = 0;
        for (Point curr : points){
            if (count > 0) {
                totalDistance += getDistance(prev, curr);
            }
            prev = curr;
            count++;
        }
        return totalDistance;
    }

    public static double getBearing(Point pt1, Point pt2) {
        double longitude1 = pt1.getLongitude();
        double longitude2 = pt2.getLongitude();
        double latitude1 = Math.toRadians(pt1.getLatitude());
        double latitude2 = Math.toRadians(pt2.getLatitude());
        double longDiff = Math.toRadians(longitude2 - longitude1);
        double y = Math.sin(longDiff) * Math.cos(latitude2);
        double x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math
                .cos(longDiff);
        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    public static Point getPosition(Point pt1, double d, double brg) {
        if (Double.doubleToRawLongBits(d) == 0) {
            return pt1;
        }

        double lat1 = Math.toRadians(pt1.getLatitude());
        double lon1 = Math.toRadians(pt1.getLongitude());
        double brgAsRadians = Math.toRadians(brg);

        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(d / EARTH_RADIUS_IN_METERS) + Math.cos(lat1) * Math
                .sin(d / EARTH_RADIUS_IN_METERS) * Math.cos(brgAsRadians));
        double x = Math.sin(brgAsRadians) * Math.sin(d / EARTH_RADIUS_IN_METERS) * Math.cos(lat1);
        double y = Math.cos(d / EARTH_RADIUS_IN_METERS) - Math.sin(lat1) * Math.sin(lat2);
        double lon2 = lon1 + Math.atan2(x, y);

        return new Point(Math.toDegrees(lat2), Math.toDegrees(lon2));
    }
}

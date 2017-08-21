package demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by XL on 8/21/2017.
 */

@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Point {
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return "Point{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

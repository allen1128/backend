package demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by XL on 8/21/2017.
 */
@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Leg {
    private Integer id;
    private Point startPosition;
    private Point endPosition;
    private Double length;
    private Double heading;
}

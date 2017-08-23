package demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by XL on 8/23/2017.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PositionInfo {
    private String runningId;
    private Point position;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private Leg leg;
    private Double distanceFromStart;

    //The speed in m/s
    private Double speed;
}

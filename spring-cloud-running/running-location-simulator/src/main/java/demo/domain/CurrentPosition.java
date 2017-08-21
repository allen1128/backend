package demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by XL on 8/21/2017.
 */

@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class CurrentPosition {
    private String runningId;
    private Point location;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private Double speed;
    private Double heading;

    private MedicalInfo medicalInfo;
}

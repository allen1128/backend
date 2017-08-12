package demo.domain;

import lombok.Data;

import javax.persistence.OneToOne;
import java.util.Date;

@Data
public class RunningInfo {
    String runningId;
    private double latitude;
    private double longitude;
    private double runningDistance;
    private double totalRunningTime;
    private int heartRate;
    private Date timeStamp;
    private UserInfo userInfo;
}

package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XL on 8/15/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="RUNNING_ANALYSIS")
public class RunningInfo {
    public RunningInfo() {
    }

    public enum HealthWarningLevel {
        LOW, NORMAL, HIGH;
    }

    @Id
    private String runningId;

    private double latitude;
    private double longitude;
    private double runningDistance;
    private double totalRunningTime;
    private int heartRate;
    private Date timeStamp;

    @Transient
    private HealthWarningLevel warningLevel = HealthWarningLevel.NORMAL;

    @Embedded
    private User userInfo;

    public void setHeartRate(int heartRate){
        this.heartRate = heartRate;
        setHearthWarningLevel(heartRate);
    }

    private void setHearthWarningLevel(int heartRate){
        if (heartRate >= 60 && heartRate <= 75){
            this.warningLevel = HealthWarningLevel.LOW;
        } else if (heartRate > 75 && heartRate <= 120) {
            this.warningLevel = HealthWarningLevel.NORMAL;
        } else if (heartRate > 120 ){
            this.warningLevel = HealthWarningLevel.HIGH;
        }
    }
}

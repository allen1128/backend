package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

/**
 * Created by XL on 8/15/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Data
@Table(name = "RUNNING_ANALYSIS")
public class RunningInfo {
    public enum HealthWarningLevel {
        LOW, NORMAL, HIGH;
    }

    @Id
    private String runningId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_ID")
    private User userInfo;

    private double latitude;

    private double longitude;

    private double runningDistance;

    private Date timestamp = new Date();

    private double totalRunningTime;

    private int heartRate;

    @Enumerated(EnumType.STRING)
    private HealthWarningLevel healthWarningLevel = HealthWarningLevel.NORMAL;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="userName", column=@Column(name="EMP_START")),
            @AttributeOverride(name="endDate", column=@Column(name="EMP_END"))
    })

    private void setHearthWarningLevel(int heartRate) {
        if (heartRate >= 60 && heartRate <= 75) {
            this.healthWarningLevel = HealthWarningLevel.LOW;
        } else if (heartRate > 75 && heartRate <= 120) {
            this.healthWarningLevel = HealthWarningLevel.NORMAL;
        } else if (heartRate > 120) {
            this.healthWarningLevel = HealthWarningLevel.HIGH;
        }
    }

    public void setHeartRate(int heartRate) {
        if (heartRate == 0) {
            Random random = new Random();
            this.heartRate = random.nextInt(141) + 60;
        }

        setHearthWarningLevel(this.heartRate);
    }

    public RunningInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    @JsonCreator
    public RunningInfo(@JsonProperty("username") String username) {
        this.userInfo = new User(username);
    }

    public RunningInfo() {
    }

    @JsonIgnore
    public double getLatitude() {
        return latitude;
    }

    @JsonProperty
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonIgnore
    public double getLongitude() {
        return longitude;
    }

    @JsonProperty
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JsonIgnore
    public double getRunningDistance() {
        return runningDistance;
    }

    @JsonProperty
    public void setRunningDistance(double runningDistance) {
        this.runningDistance = runningDistance;
    }

    @JsonIgnore
    public Date getTimestamp() {
        return timestamp;
    }

    @JsonProperty
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

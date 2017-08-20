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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public User userInfo;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private double runningDistance;

    @Column
    private Date timestamp = new Date();

    @Column
    private double totalRunningTime;

    @Column
    private int heartRate;

    @Enumerated(EnumType.STRING)
    private HealthWarningLevel healthWarningLevel = HealthWarningLevel.NORMAL;

    public RunningInfo() {
    }

    public RunningInfo(User userInfo) {
        this.userInfo = userInfo;
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

    @JsonProperty
    public Long getUserId() {
        return userInfo.getUserId();
    }

    @JsonProperty
    public String getUserName() {
        return userInfo.getUsername();
    }

    @JsonProperty
    public String getUserAddress() {
        return userInfo.getAddress();
    }

    @JsonIgnore
    public User getUserInfo() {
        return userInfo;
    }

    @JsonProperty
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    @JsonCreator
    public RunningInfo(@JsonProperty("username") String username) {
        this.userInfo = new User(username);
    }

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
}

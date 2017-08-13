package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XL on 8/11/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "LOCATION")
public class Location {
    enum GpsStatus {
        EXCELLENT, OK, UNRELIABLE, BAD, NOTFIX, UNKNOWN;
    }

    public enum RunnerMovementType {
        STOPPED, IN_MOTION;

        public boolean isMoving() {
            return this != STOPPED;
        }
    }

    @Id
    @GeneratedValue
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bfr", column = @Column(name = "medical_bfr")),
            @AttributeOverride(name = "fmi", column = @Column(name = "medical_fmi"))
    })
    private MedicalInfo medicalInfo;

    @Embedded
    @AttributeOverride(name = "bandMake", column = @Column(name = "unitinfo_band_make"))
    private UnitInfo unitInfo;

    private double latitude;
    private double longitude;
    private String heading;
    private double gpsSpeed;
    private GpsStatus gpsStatus;
    private double odometer;
    private double totalRunningTime;
    private double totalIdleTime;
    private double totalCalorieBurnt;
    private String address;
    private Date timestamp = new Date();
    private String gearProvider;
    private RunnerMovementType runnerMovementType;
    private String serviceType;

    public Location(){
    }

    @JsonCreator
    public Location(@JsonProperty("runningId") String runningId){
        this.unitInfo = new UnitInfo(runningId);
    }

    public Location(UnitInfo unitInfo){
        this.unitInfo = unitInfo;
    }

    public String getRunningId(){
        return (this.unitInfo == null) ? null : this.unitInfo.getRunningId();
    }
}

package demo.task;

import demo.domain.*;
import demo.service.GpsSimulatorFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by XL on 8/23/2017.
 */
public class LocationSimulator implements Runnable {

    private long id;
    private AtomicBoolean cancel = new AtomicBoolean();
    private Double speedInMps; // In meters/sec
    private boolean shouldMove;
    private boolean exportPositionsToMessaging = true;

    private Integer reportInterval = 500; // millisecs at which to send position reports
    private PositionInfo positionInfo = null;
    private List<Leg> legs;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private String runningId;

    private Integer secondsToError = 45;
    private Point startPoint;
    private Date executionStartTime;
    private MedicalInfo medicalInfo;

    public LocationSimulator(GpsSimulatorRequest gpsSimulatorRequest){
        this.shouldMove = gpsSimulatorRequest.isMove();
        this.exportPositionsToMessaging = gpsSimulatorRequest.isExportPositionsToMessaging();
        this.speedInMps = gpsSimulatorRequest.getSpeed();
        this.reportInterval = gpsSimulatorRequest.getReportInterval();
        this.secondsToError = gpsSimulatorRequest.getSecondsToError();
        this.runningId = gpsSimulatorRequest.getRunningId();
        this.runnerStatus = gpsSimulatorRequest.getRunnerStatus();
        this.medicalInfo = gpsSimulatorRequest.getMedicalInfo();
    }

    @Override
    public void run() {
    }

    void destroy() {
        positionInfo = null;
    }

    public AtomicBoolean getCancel() {
        return cancel;
    }

    public void setCancel(AtomicBoolean cancel) {
        this.cancel = cancel;
    }

    public Double getSpeedInMps() {
        return speedInMps;
    }

    public void setSpeedInMps(Double speedInMps) {
        this.speedInMps = speedInMps;
    }

    public boolean isShouldMove() {
        return shouldMove;
    }

    public void setShouldMove(boolean shouldMove) {
        this.shouldMove = shouldMove;
    }

    public boolean isExportPositionsToMessaging() {
        return exportPositionsToMessaging;
    }

    public void setExportPositionsToMessaging(boolean exportPositionsToMessaging) {
        this.exportPositionsToMessaging = exportPositionsToMessaging;
    }

    public Integer getReportInterval() {
        return reportInterval;
    }

    public void setReportInterval(Integer reportInterval) {
        this.reportInterval = reportInterval;
    }

    public PositionInfo getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(PositionInfo positionInfo) {
        this.positionInfo = positionInfo;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public RunnerStatus getRunnerStatus() {
        return runnerStatus;
    }

    public void setRunnerStatus(RunnerStatus runnerStatus) {
        this.runnerStatus = runnerStatus;
    }

    public String getRunningId() {
        return runningId;
    }

    public void setRunningId(String runningId) {
        this.runningId = runningId;
    }

    public Integer getSecondsToError() {
        return secondsToError;
    }

    public void setSecondsToError(Integer secondsToError) {
        this.secondsToError = secondsToError;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Date getExecutionStartTime() {
        return executionStartTime;
    }

    public void setExecutionStartTime(Date executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    public MedicalInfo getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(MedicalInfo medicalInfo) {
        this.medicalInfo = medicalInfo;
    }
}

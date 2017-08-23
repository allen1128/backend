package demo.task;

import demo.domain.*;
import demo.service.GpsSimulatorFactory;
import demo.service.PositionService;
import demo.util.NavUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by XL on 8/23/2017.
 */
public class LocationSimulator implements Runnable {

    private PositionService positionInfoService;
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

    public LocationSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
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
        try {
            executionStartTime = new Date();
            if (cancel.get()) {
                destroy();
                return;
            }

            while (!Thread.interrupted()) {
                long startTime = new Date().getTime();
                if (positionInfo != null) {
                    if (shouldMove) {
                        moveRunningLocation();
                        positionInfo.setSpeed(speedInMps);
                    } else {
                        positionInfo.setSpeed(0.0);
                    }


                    if (this.secondsToError > 0 && startTime - executionStartTime.getTime() >= this.secondsToError * 1000) {
                        this.runnerStatus = RunnerStatus.SUPPLY_NOW;
                    }

                    positionInfo.setRunnerStatus(this.runnerStatus);

                    final MedicalInfo medicalInfoToUse;
                    switch (this.runnerStatus) {
                        case SUPPLY_INFO:
                        case SUPPLY_NOW:
                        case STOP_NOW:
                            medicalInfoToUse = this.medicalInfo;
                            break;
                        default:
                            medicalInfoToUse = null;
                            break;
                    }

                    final CurrentPosition currentPosition = new CurrentPosition(
                            positionInfo.getRunningId(),
                            new Point(positionInfo.getPosition().getLatitude(), positionInfo.getPosition().getLongitude()),
                            positionInfo.getRunnerStatus(),
                            positionInfo.getSpeed(),
                            positionInfo.getLeg().getHeading(),
                            medicalInfoToUse
                    );

                    positionInfoService.processPositionInfo(id, currentPosition, this.exportPositionsToMessaging);

                }
                sleep(startTime);
            }
        } catch (InterruptedException ex) {
        } finally {
            destroy();
        }
    }

    public void sleep(long startTime) throws InterruptedException {
        long endTime = new Date().getTime();
        long elapsedTime = endTime - startTime;
        long sleepTime = reportInterval - elapsedTime > 0 ? reportInterval - elapsedTime : 0;
        Thread.sleep(sleepTime);
    }

    private void moveRunningLocation() {
        Double distance = speedInMps * reportInterval / 1000.0;
        Double distanceFromStart = positionInfo.getDistanceFromStart() + distance;
        Double excess = 0.0;

        for (int i = positionInfo.getLeg().getId(); i < legs.size(); i++) {
            Leg currentLeg = legs.get(i);
            excess = distanceFromStart > currentLeg.getLength() ? distanceFromStart - currentLeg.getLength() : 0.0;

            if (Double.doubleToRawLongBits(excess) == 0) {
                positionInfo.setDistanceFromStart(distanceFromStart);
                positionInfo.setLeg(currentLeg);
                Point newPosition = NavUtil.getPosition(currentLeg.getStartPosition(), distanceFromStart, currentLeg.getHeading());
                positionInfo.setPosition(newPosition);
                return;
            }

            distanceFromStart = excess;
        }

        setStartPosition();
    }

    public void setStartPosition() {
        positionInfo = new PositionInfo();
        positionInfo.setRunningId(this.runningId);
        Leg leg = legs.get(0);

        positionInfo.setLeg(leg);
        positionInfo.setPosition(leg.getStartPosition());
        positionInfo.setDistanceFromStart(0.0);
    }

    void destroy() {
        positionInfo = null;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public PositionInfo getCurrentPosition(){
        return positionInfo;
    }

    public void setPositionInfo(PositionInfo positionInfo) {
        this.positionInfo = positionInfo;
    }

    public void setCurrentPosition(PositionInfo currentPosition) {
        this.positionInfo = currentPosition;
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

    public PositionService getPositionInfoService() {
        return positionInfoService;
    }

    public void setPositionInfoService(PositionService positionInfoService) {
        this.positionInfoService = positionInfoService;
    }
}

package demo.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Future;

/**
 * Created by XL on 8/23/2017.
 */

@Data
@AllArgsConstructor
public class LocationSimulatorInstance {
    private long instanceId;
    private LocationSimulator locationSimulator;
    private Future<?> locationSimulatorTask;

    @Override
    public String toString() {
        return "LocationSimulatorInstance{" +
                "instanceId=" + instanceId +
                ", locationSimulator=" + locationSimulator +
                ", locationSimulatorTask=" + locationSimulatorTask +
                '}';
    }
}

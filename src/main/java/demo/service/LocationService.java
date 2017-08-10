package demo.service;

import demo.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by I827417 on 8/11/2017.
 */
public interface LocationService {
    List<Location> saveRunningLocations(List<Location> runningLocations);

    void deleteAll();

    Page<Location> findByRunningMovementType(String movementType, Pageable pageable);
    Page<Location> findByRunnerId(String runnerId, Pageable pageable);
}

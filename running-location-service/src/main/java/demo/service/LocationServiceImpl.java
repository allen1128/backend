package demo.service;

import demo.domain.Location;
import demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XL on 8/11/2017.
 */

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<demo.domain.Location> saveRunningLocations(List<demo.domain.Location> runningLocations) {
        return locationRepository.save(runningLocations);
    }

    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<demo.domain.Location> findByRunningMovementType(String movementType, Pageable pageable) {
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType), pageable);
    }

    @Override
    public Page<demo.domain.Location> findByRunnerId(String runnerId, Pageable pageable) {
        return locationRepository.findByUnitInfoRunningId(runnerId, pageable);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}

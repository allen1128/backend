package demo.repository;

import demo.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


/**
 * Created by I827417 on 8/11/2017.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
    Page<Location> findByRunnerMovementType(@Param("movementType") Location.RunnerMovementType movementType, Pageable pageable);

    Page<Location> findByUnitInfoRunningId(@Param("runningId") String runningId, Pageable pageable);

}

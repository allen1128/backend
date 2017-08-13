package demo.repository;

import demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by I827417 on 8/13/2017.
 */
public interface RunningInfoRepository extends JpaRepository<RunningInfo, String> {
    Page<RunningInfo> findByUserInfoUserName(@Param("userName") String userName);
    Page<RunningInfo> findByRunningId(@Param("runningId") String runningId);
}

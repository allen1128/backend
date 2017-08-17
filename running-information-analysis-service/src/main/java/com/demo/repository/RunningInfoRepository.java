package com.demo.repository;

import com.demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by XL on 8/15/2017.
 */

public interface RunningInfoRepository extends JpaRepository<RunningInfo, String> {
    void delete(@Param("runningId") String runningId);

    RunningInfo findByRunningId(@Param("runningId") String runningId);

    Page<RunningInfo> findAllByOrderByHealthWarningLevelDesc(Pageable pageable);
}

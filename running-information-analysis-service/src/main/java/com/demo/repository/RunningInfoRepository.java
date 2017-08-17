package com.demo.repository;

import com.demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by XL on 8/15/2017.
 */
public interface RunningInfoRepository extends JpaRepository<RunningInfo, Long>{
    void deleteByRunningId(@Param("runningId") String runningId);
    RunningInfo findByRunningId(@Param("runningId") String runningId);
    Page<RunningInfo> findByUserInfoUserName(@Param("userName") String userName, Pageable pageable);
}

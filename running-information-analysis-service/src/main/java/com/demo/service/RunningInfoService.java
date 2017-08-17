package com.demo.service;

import com.demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by XL on 8/11/2017.
 */

public interface RunningInfoService {
    List<RunningInfo> saveRunningInfo(List<RunningInfo> runningInfos);
    RunningInfo findByRunningId(String runningId);
    Page<RunningInfo> findByUserName(String userName, Pageable pageable);
    void deleteByRunningId(String runningId);
}

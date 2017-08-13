package demo.service;

import demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XL on 8/11/2017.
 */

public interface RunningInfoService {
    List<RunningInfo> saveRunningInfo(List<RunningInfo> runningInfo);
    void deleteAll();
    Page<RunningInfo> findByUserInfoUserName(String userName, Pageable pageable);
    RunningInfo findByRunningId(String runningId);
    List<RunningInfo> findAll();
}

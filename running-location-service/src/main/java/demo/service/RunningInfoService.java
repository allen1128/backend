package demo.service;

import demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by I827417 on 8/13/2017.
 */
@Service
public interface RunningInfoService {
    List<RunningInfo> saveRunningInfo(List<RunningInfo> runningInfo);
    void deleteAll();
    Page<RunningInfo> findByUserInfoUserName(String userName, Pageable pageable);
    RunningInfo findByRunningId(String runningId);
    List<RunningInfo> findAll();
}

package demo.service;

import demo.domain.RunningInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by I827417 on 8/13/2017.
 */
public class RunningInfoServiceImpl implements RunningInfoService {

    @Autowired
    RunningInfoService runningInfoService;

    @Override
    public List<RunningInfo> saveRunningInfo(List<RunningInfo> runningInfo) {
        /*if (runningInfo.getHeartRate() == 0){
            runningInfo.setHeartRate(new Random(140).nextInt() + 60);
        }*/
        return runningInfoService.saveRunningInfo(runningInfo);
    }

    @Override
    public void deleteAll() {
        runningInfoService.deleteAll();
    }

    @Override
    public Page<RunningInfo> findByUserInfoUserName(String userName, Pageable pageable) {
        return runningInfoService.findByUserInfoUserName(userName, pageable);
    }

    @Override
    public RunningInfo findByRunningId(String runningId) {
        return runningInfoService.findByRunningId(runningId);
    }

    @Override
    public List<RunningInfo> findAll() {
        return runningInfoService.findAll();
    }
}

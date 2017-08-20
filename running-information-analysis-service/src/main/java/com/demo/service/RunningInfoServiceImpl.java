package com.demo.service;

import com.demo.domain.RunningInfo;
import com.demo.repository.RunningInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XL on 8/11/2017.
 */

@Service
public class RunningInfoServiceImpl implements RunningInfoService {

    @Autowired
    RunningInfoRepository runningInfoRepository;

    @Override
    public List<RunningInfo> saveRunningInfo(List<RunningInfo> runningInfos) {
        return runningInfoRepository.save(runningInfos);
    }

    @Override
    public RunningInfo findByRunningId(String runningId) {
        return runningInfoRepository.findByRunningId(runningId);
    }

    @Override
    public Page<RunningInfo> findAll(Pageable pageable) {
        return runningInfoRepository.findAllByOrderByHeartRateDesc(pageable);
    }

    @Override
    public void deleteByRunningId(String runningId) {
        runningInfoRepository.delete(runningId);
    }
}

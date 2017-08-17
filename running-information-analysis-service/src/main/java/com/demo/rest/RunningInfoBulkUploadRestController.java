package com.demo.rest;

import com.demo.domain.RunningInfo;
import com.demo.service.RunningInfoService;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by XL on 8/11/2017.
 */

@RestController
public class RunningInfoBulkUploadRestController {

    private final int PAGE_SIZE = 2;
    @Autowired
    RunningInfoService runningInfoService;

    @RequestMapping(value = "runninginfo/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInfo> runningInfos) {
        runningInfoService.saveRunningInfo(runningInfos);
    }

    @RequestMapping(value = "runninginfo/runningid/{runningId}", method = RequestMethod.GET)
    public RunningInfo findByRunningId(@PathVariable String runningId) {
        return runningInfoService.findByRunningId(runningId);
    }

    @RequestMapping(value = "runninginfo/runningid/{runningId}", method = RequestMethod.DELETE)
    public void deleteByRunningId(@PathVariable String runningId) {
        runningInfoService.deleteByRunningId(runningId);
    }

    @RequestMapping(value = "runninginfo/", method = RequestMethod.GET)
    public Page<RunningInfo> findAll(@RequestParam(name = "page") int page) {
        return runningInfoService.findAll(new PageRequest(page, PAGE_SIZE));
    }
}

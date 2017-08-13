package demo.rest;

import demo.domain.RunningInfo;
import demo.service.RunningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by XL on 8/11/2017.
 */

@RestController
public class RunningInfoBulkUploadRestController {

    @Autowired
    RunningInfoService runningInfoService;

    @RequestMapping(value="runninginfo/", method= RequestMethod.POST)
    public void upload(@RequestBody List<RunningInfo> runningInfos){
        runningInfoService.saveRunningInfo(runningInfos);
    }

    @RequestMapping(value="runninginfo/", method=RequestMethod.GET)
    public List<RunningInfo> findAll(){
        return runningInfoService.findAll();
    }

    @RequestMapping(value="runninginfo/runningid/{runningid}", method=RequestMethod.GET)
    public RunningInfo findByRunningId(@PathVariable String runningId){
        return runningInfoService.findByRunningId(runningId);
    }

    @RequestMapping(value="runninginfo/{userName}", method=RequestMethod.GET)
    public Page<RunningInfo> findByUserName(@PathVariable String userName, @RequestParam(name="page") int page, @RequestParam(name="size") int size){
        return runningInfoService.findByUserInfoUserName(userName, new PageRequest(page, size));
    }

}

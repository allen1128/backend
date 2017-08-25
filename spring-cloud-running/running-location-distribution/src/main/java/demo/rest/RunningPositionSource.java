package demo.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XL on 8/25/2017.
 */

//@EnableBinding(Source.class)
@RestController
public class RunningPositionSource {
    @RequestMapping(path="/api/locations", method= RequestMethod.POST)
    public void locations(@RequestBody String positionInfo){
        //extract

        //transform

        //publish
    }
}

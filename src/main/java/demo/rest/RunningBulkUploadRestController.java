package demo.rest;

import demo.domain.Location;
import demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import java.util.List;

/**
 * Created by I827417 on 8/11/2017.
 */

@Controller
public class RunningBulkUploadRestController {
    @Autowired
    private LocationService locationService;

    @RequestMapping(value="/running", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(List<Location> locations){
        locationService.saveRunningLocations(locations);
    }

    @RequestMapping(value="/purge", method=RequestMethod.DELETE)
    public void deleteAll(){
        locationService.deleteAll();
    }

    @RequestMapping(value="/running/{movementType}", method=RequestMethod.GET)
    public void findByMovementType(@PathVariable String movementType, @RequestParam(name="page") int page, @RequestParam(name="size") int size){
        locationService.findByRunningMovementType(movementType, new PageRequest(page, size));
    }
}

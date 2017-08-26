package com.ofo.rest;

import com.ofo.domain.Restaurant;
import com.ofo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */

@RequestMapping(value="restaurants/")
@RestController
public class RestaurantRestController {

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value="search/{restaurantname}", method= RequestMethod.POST)
    public Restaurant findByRestaurantName(@PathVariable String restaurantName){
        return restaurantService.findByName(restaurantName);
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Restaurant> restaurants){
        restaurantService.saveRestaurantInfo(restaurants);
    }

    @RequestMapping(value="{restaurantid}", method=RequestMethod.DELETE)
    public void delete(@PathVariable String restaurantId){
        restaurantService.deleteByRestaurantId(restaurantId);
    }
}

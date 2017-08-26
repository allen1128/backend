package com.ofo.rest;

import com.ofo.domain.Restaurant;
import com.ofo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}

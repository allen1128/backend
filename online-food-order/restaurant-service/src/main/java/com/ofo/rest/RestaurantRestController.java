package com.ofo.rest;

import com.ofo.domain.Address;
import com.ofo.domain.CreditCard;
import com.ofo.domain.Dish;
import com.ofo.domain.Restaurant;
import com.ofo.service.RestaurantService;
import com.ofo.service.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XL on 8/26/2017.
 */

@RequestMapping(value="/restaurants")
@RestController
public class RestaurantRestController {

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value="/search/{restaurantName}", method= RequestMethod.POST)
    public List<Restaurant> findByRestaurantName(@PathVariable String restaurantName){
        return restaurantService.findByName(restaurantName);
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Restaurant> restaurants){
        restaurantService.saveRestaurantInfo(restaurants);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<Restaurant> findAll(){
        return restaurantService.findAll();
    }

    @RequestMapping(value="/{restaurantId}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Long restaurantId){
        restaurantService.deleteByRestaurantId(restaurantId);
    }

    @RequestMapping(value="/{restaurantId}", method=RequestMethod.GET)
    public Restaurant findOne(@PathVariable Long restaurantId){
        return restaurantService.findOne(restaurantId);
    }

    @RequestMapping(value="/addtocart/{dishId}/{quantity}", method=RequestMethod.POST)
    public void addToCart(@PathVariable Long dishId, @PathVariable int quantity){
        restaurantService.addToCart(dishId, quantity);
    }

    @RequestMapping(value="/remotefromcart/{dishId}", method=RequestMethod.POST)
    public void remoteFromCart(@PathVariable Long dishId){
        restaurantService.removeFromCart(dishId);
    }

    @RequestMapping(value="/addnotetocart", method=RequestMethod.POST)
    public void addNote(@RequestBody String note){
        restaurantService.addNoteToCart(note);
    }

    @RequestMapping(value="pay", method = RequestMethod.POST)
    public void pay(@RequestBody CreditCard creditCard){
        restaurantService.pay(creditCard);
    }
}

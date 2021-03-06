package com.ofo.rest;

import com.ofo.domain.Address;
import com.ofo.domain.CreditCard;
import com.ofo.domain.Receipt;
import com.ofo.domain.Restaurant;
import com.ofo.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */

@RequestMapping(value = "/restaurants")
@RestController
@Slf4j
public class RestaurantRestController {

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/search/{restaurantName}", method = RequestMethod.POST)
    public List<Restaurant> findByRestaurantName(@PathVariable String restaurantName) {
        return restaurantService.findByName(restaurantName);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Restaurant> restaurants) {
        restaurantService.saveRestaurantInfo(restaurants);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Restaurant> findAll() {
        return restaurantService.findAll();
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long restaurantId) {
        restaurantService.deleteByRestaurantId(restaurantId);
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
    public Restaurant findOne(@PathVariable Long restaurantId) {
        return restaurantService.findOne(restaurantId);
    }

    @RequestMapping(value = "/addtocart/{cartId}/{dishId}/{quantity}", method = RequestMethod.POST)
    public Long addToCart(@PathVariable Long cartId, @PathVariable Long dishId, @PathVariable int quantity) {
        return restaurantService.addToCart(cartId, dishId, quantity);
    }

    @RequestMapping(value = "/removefromcart/{cartId}/{dishId}", method = RequestMethod.POST)
    public Long remoteFromCart(@PathVariable Long cartId, @PathVariable Long dishId) {
        return restaurantService.removeFromCart(cartId, dishId);
    }

    @RequestMapping(value = "/addnotetocart/{cartId}", method = RequestMethod.POST)
    public Long addNote(@PathVariable Long cartId, @RequestBody String note) {
        return restaurantService.addNoteToCart(cartId, note);
    }

    @RequestMapping(value = "/deliveryaddress/{cartId}", method = RequestMethod.POST)
    public Long addDeliveryAddress(@PathVariable Long cartId, @RequestBody Address address) {
        return restaurantService.addAddress(cartId, address);
    }

    @RequestMapping(value = "pay/{cartId}", method = RequestMethod.POST)
    public Receipt pay(@PathVariable Long cartId, @RequestBody CreditCard creditCard) {
        return restaurantService.pay(cartId, creditCard);
    }

    @RequestMapping(value = "/username/{userName}", method = RequestMethod.POST)
    public void setUserName(@PathVariable String userName, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        log.info("username: " + session.getAttribute("userName"));
    }
}

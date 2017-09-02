package com.ofo.rest;

import com.ofo.domain.Cart;
import com.ofo.domain.CartItem;
import com.ofo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
@RestController
@RequestMapping(value = "/cart")
public class CartRestController {
    @Autowired
    private CartService orderService;

    @RequestMapping(value = "/{userName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cart create(@RequestBody Set<CartItem> cartItems, @PathVariable String userName) {
        return orderService.create(cartItems, userName);
    }

    @RequestMapping(value = "/{cartId}/add", method = RequestMethod.POST)
    public Cart update(@PathVariable Long cartId, @RequestBody Set<CartItem> cartItems) {
        return orderService.update(cartId, cartItems);
    }

    @RequestMapping(value = "/{cartId}/pay", method = RequestMethod.POST)
    public void pay(@PathVariable Long orderId) {
        orderService.pay(orderId);
    }

}

package com.ofo.rest;

import com.ofo.domain.Cart;
import com.ofo.domain.CartItem;
import com.ofo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
@RestController
@RequestMapping(value = "/cart")
public class CartRestController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/pay/{userName}", method = RequestMethod.POST)
    public void pay(@PathVariable String userName ) {
        cartService.pay(userName);
    }

    @RequestMapping(value="/add/{userName}/{externalItemId}/{price}/{name}/{quantity}", method=RequestMethod.POST)
    public Long add(@PathVariable String userName, @PathVariable Long externalItemId, @PathVariable float price, @PathVariable String name, @PathVariable int quantity){
        Set<CartItem> cartItems = new HashSet<CartItem>();
        cartItems.add(new CartItem(externalItemId, price, name, quantity));
        Cart cart = cartService.creatOrUpdate(cartItems, userName);
        return cart.getCartId();
    }

    @RequestMapping(value="/remove/{userName}/{externalItemId}", method=RequestMethod.POST)
    public Long remove(@PathVariable String userName, @PathVariable Long externalItemId){
        Cart cart = cartService.remove(externalItemId, userName);
        return cart.getCartId();
    }

    @RequestMapping(value="/addnote", method=RequestMethod.POST)
    public Long addNote(@PathVariable String userName, @PathVariable String note){
        Cart cart = cartService.addNote(note, userName);
        return cart.getCartId();
    }

}

package com.ofo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofo.domain.Address;
import com.ofo.domain.Cart;
import com.ofo.domain.CartItem;
import com.ofo.domain.CreditCard;
import com.ofo.service.CartService;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
@RestController
@RequestMapping(value = "/cart")
@Slf4j
public class CartRestController {
    @Autowired
    private CartService cartService;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public boolean pay(Long cartId, String creditCardStr) {
        boolean result = false;

        try {
            CreditCard creditCard = this.objectMapper.readValue(creditCardStr, CreditCard.class);
            result = cartService.pay(cartId, creditCard);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public Long add(String userName, Long externalItemId, float price, String name, int quantity){
        CartItem ci = new CartItem(externalItemId, price, name, quantity);
        Cart cart = cartService.creatOrUpdateCartItem(ci, userName);
        return cart.getCartId();
    }

    @RequestMapping(value="/addnote", method=RequestMethod.POST)
    public Long addNote(String note, String userName){
        Cart cart = cartService.addNote(note, userName);
        return cart.getCartId();
    }

    @RequestMapping(value ="/addaddress",method = RequestMethod.POST)
    public Long addAddress(String addressStr, String userName){
        Long result = -1l;
        try {
            Address address = this.objectMapper.readValue(addressStr, Address.class);
            Cart cart = cartService.addAddress(address, userName);
            result = cart.getCartId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}

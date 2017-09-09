package com.ofo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofo.domain.*;
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
    public Receipt pay(Long cartId, String creditCardStr) {
        if (cartId == null || creditCardStr == null){
            return null;
        }

        try {
            CreditCard creditCard = this.objectMapper.readValue(creditCardStr, CreditCard.class);
            cartService.pay(cartId, creditCard);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cartService.buildReceipt(cartId);
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public Long add(Long cartId, String userName, Long externalItemId, Float price, String name, int quantity){
        Long result = -1l;

        CartItem ci = new CartItem(externalItemId, price, name, quantity);
        Cart cart = cartService.creatOrUpdateCartItem(cartId, ci, userName);
        if (cart != null) {
            result = cart.getCartId();
        }

        return result;
    }

    @RequestMapping(value="/addnote", method=RequestMethod.POST)
    public Long addNote(Long cartId, String note){
        Long result = -1l;

        Cart cart = cartService.addNote(cartId, note);
        if (cart != null) {
            result = cart.getCartId();
        }

        return result;
    }

    @RequestMapping(value ="/addaddress",method = RequestMethod.POST)
    public Long addAddress(Long cartId, String addressStr){
        Long result = -1l;

        try {
            Address address = this.objectMapper.readValue(addressStr, Address.class);
            Cart cart = cartService.addAddress(cartId, address);
            result = cart.getCartId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}

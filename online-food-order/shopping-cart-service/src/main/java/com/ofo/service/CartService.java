package com.ofo.service;

import com.ofo.domain.Cart;
import com.ofo.domain.CartItem;

import java.util.List;
import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
public interface CartService {
    Cart create(Cart cart);
    Cart create(Set<CartItem> cartItems, String userName);
    Cart update(Long cartId, Set<CartItem> cartItems);
    boolean isPaid(Long cartId);
    void pay(Long cartId);
}

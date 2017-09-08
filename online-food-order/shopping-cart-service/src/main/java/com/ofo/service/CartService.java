package com.ofo.service;

import com.ofo.domain.*;

import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
public interface CartService {
    Cart update(Long cartId, Set<CartItem> cartItems);
    boolean isPaid(Long cartId);
    Payment pay(Long cartId, CreditCard creditCard);
    Cart creatOrUpdateCartItem(Long cartId, CartItem cartItem, String userName);
    Cart removeCartItemById(Long cartId, Long externalItemId);
    Cart addNote(Long cartId, String note);
    void updatePaymentDone(Payment payment);
    Cart addAddress(Long cartId, Address address);
}

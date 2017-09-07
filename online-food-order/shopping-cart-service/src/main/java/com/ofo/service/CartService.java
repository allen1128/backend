package com.ofo.service;

import com.ofo.domain.*;

import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
public interface CartService {
    Cart update(Long cartId, Set<CartItem> cartItems);
    boolean isPaid(Long cartId);
    boolean pay(Long cartId, CreditCard creditCard);
    Cart creatOrUpdateCartItem(CartItem cartItem, String userName);
    Cart removeCartItemById(Long externalItemId, String userName);
    Cart addNote(String note, String userName);
    void updatePaymentDone(Payment payment);
    Cart addAddress(Address address, String userName);
}

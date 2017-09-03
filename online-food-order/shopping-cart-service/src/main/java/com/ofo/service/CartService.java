package com.ofo.service;

import com.ofo.domain.Cart;
import com.ofo.domain.CartItem;
import com.ofo.domain.Payment;

import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
public interface CartService {
    Cart update(Long cartId, Set<CartItem> cartItems);
    boolean isPaid(Long cartId);
    void pay(String userName);
    Cart creatOrUpdate(Set<CartItem> cartItems, String userName);
    Cart remove(Long externalItemId, String userName);
    Cart addNote(String note, String userName);
    void updatePaymentDone(Payment payment);
}

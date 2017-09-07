package com.ofo.service;

import com.ofo.domain.*;
import com.ofo.domain.Cart.CartType;
import com.ofo.repository.CartRepository;
import com.ofo.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */
@Slf4j
@EnableBinding(Source.class)
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MessageChannel output;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Cart update(Long cartId, Set<CartItem> cartItems) {
        Cart cart = cartRepository.getOne(cartId);
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public boolean isPaid(Long cartId) {
        boolean result = false;
        Cart cart = cartRepository.getOne(cartId);
        if (cart != null) {
            Payment payment = paymentRepository.findByCartId(cart.getCartId());
            if (payment.getCompletedAt() != null) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean pay(Long cartId, CreditCard creditCard) {
        Cart cart = cartRepository.findOne(cartId);
        Payment payment = paymentRepository.findByCartId(cartId);

        if (payment != null && payment.getCompletedAt() != null){
            return true; //paid
        }

        if (payment == null) {
            payment = new Payment(creditCard, cart.getCartId(), cart.getTotal());
            paymentRepository.save(payment);
        } else {
            payment.setCreditCard(creditCard);
        }

        this.output.send(MessageBuilder.withPayload(payment).build());
        return false;
    }

    @Override
    public Cart creatOrUpdateCartItem(CartItem cartItem, String userName) {
        Cart cart = cartRepository.findByOrderBy(userName);

        if (cart == null) {
            cart = new Cart(userName, CartType.FOOD);
        }

        cart.updateCartItem(cartItem);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart removeCartItemById(Long externalItemId, String userName) {
        Cart cart = cartRepository.findByOrderBy(userName);
        if (cart != null){
            cart.remoteCartItemById(externalItemId);
        }

        return cart;
    }

    @Override
    public Cart addNote(String note, String userName) {
        Cart cart = cartRepository.findByOrderBy(userName);
        if (cart != null) {
            cart.setNote(note);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public void updatePaymentDone(Payment payment) {
        Long cartId = payment.getCartId();
        Payment curr = paymentRepository.findByCartId(cartId);
        curr.setCompletedAt(payment.getCompletedAt());
        paymentRepository.save(curr);
    }

    @Override
    public Cart addAddress(Address address, String userName) {
        Cart cart = cartRepository.findByOrderBy(userName);
        if (cart != null) {
            cart.setAddress(address);
            cartRepository.save(cart);
        }
        return cart;
    }
}

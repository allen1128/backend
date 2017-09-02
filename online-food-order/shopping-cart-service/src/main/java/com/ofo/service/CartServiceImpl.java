package com.ofo.service;

import com.ofo.domain.Cart;
import com.ofo.domain.CartItem;
import com.ofo.domain.Payment;
import com.ofo.repository.CartRepository;
import com.ofo.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart create(Set<CartItem> cartItems, String userName) {
        Cart cart = new Cart();
        cart.setOrdedBy(userName);
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        return cart;
    }

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
        if (cart != null){
            Payment payment = paymentRepository.findByCartId(cart.getCartId());
            if (payment.getCompletedAt() != null){
                result = true;
            }
        }
        return result;
    }

    @Override
    public void pay(Long orderId) {
//        String paymentService = "http://payment-service";
//        log.info("sending payment request to payment service");
        Cart cart = cartRepository.findOne(orderId);
        //Payment payment = new Payment(cart.getOrdedBy(), cart.getTotal());
        //restTemplate.postForLocation(paymentService + "/api/paynow", payment);
        Payment payment = paymentRepository.findByCartId(cart.getCartId());
        if (payment == null){
            payment = new Payment();
            payment.setCartId(cart.getCartId());
            payment.setTotal(cart.getTotal());
            paymentRepository.save(payment);
        }
        this.output.send(MessageBuilder.withPayload(payment).build());
    }
}

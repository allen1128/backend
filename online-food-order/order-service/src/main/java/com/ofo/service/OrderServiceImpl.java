package com.ofo.service;

import com.ofo.domain.Order;
import com.ofo.domain.Payment;
import com.ofo.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by XL on 8/27/2017.
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void pay(String orderId) {
        String paymentService = "http://payment-service";
        log.info("sending payment request to payment service");
        Order order = orderRepository.findOne(orderId);
        Payment payment = new Payment(order.getOrdedBy(), order.getPrice());
        restTemplate.postForLocation(paymentService + "/api/paynow", payment);
    }
}

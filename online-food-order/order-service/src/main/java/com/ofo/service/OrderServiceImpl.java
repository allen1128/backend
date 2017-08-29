package com.ofo.service;

import com.ofo.domain.Order;
import com.ofo.domain.Payment;
import com.ofo.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by XL on 8/27/2017.
 */
@Slf4j
@EnableBinding(Source.class)
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageChannel output;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void pay(String orderId) {
//        String paymentService = "http://payment-service";
//        log.info("sending payment request to payment service");
        Order order = orderRepository.findOne(orderId);
        Payment payment = new Payment(order.getOrdedBy(), order.getPrice());
        //restTemplate.postForLocation(paymentService + "/api/paynow", payment);
        this.output.send(MessageBuilder.withPayload(payment).build());
    }
}

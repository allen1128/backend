package com.ofo.service;

import com.ofo.domain.Order;
import com.ofo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by XL on 8/27/2017.
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }
}

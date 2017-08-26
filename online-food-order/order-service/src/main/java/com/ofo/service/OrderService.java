package com.ofo.service;

import com.ofo.domain.Order;

import java.util.List;

/**
 * Created by XL on 8/27/2017.
 */
public interface OrderService {
    Order create(Order order);
    void pay(String orderId);
}

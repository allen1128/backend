package com.ofo.rest;

import com.ofo.domain.Dish;
import com.ofo.domain.Order;
import com.ofo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by XL on 8/27/2017.
 */
@RestController
@RequestMapping(value="order/")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }

    public void pay(@PathVariable String orderId){
        orderService.pay(orderId);
    }
}

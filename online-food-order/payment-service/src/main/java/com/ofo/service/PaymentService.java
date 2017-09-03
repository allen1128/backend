package com.ofo.service;

import com.ofo.domain.Payment;

/**
 * Created by XL on 8/27/2017.
 */
public interface PaymentService {
    void process(Payment payment) throws InterruptedException;
}

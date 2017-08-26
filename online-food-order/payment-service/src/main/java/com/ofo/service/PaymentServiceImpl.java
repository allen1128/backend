package com.ofo.service;

import com.ofo.domain.Payment;
import com.ofo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by XL on 8/27/2017.
 */
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}

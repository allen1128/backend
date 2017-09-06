package com.ofo.service;

import com.ofo.domain.Payment;
import com.ofo.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by XL on 8/27/2017.
 */
@Service
@EnableBinding(Source.class)
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private MessageChannel output;

    @Override
    public void process(Payment payment) throws InterruptedException {
        payment.setCreatedAt(new Date());
        //call the credit card companies apis
        Thread.sleep(2000);

        payment.setCompletedAt(new Date());
        paymentRepository.save(payment);

        log.info("Sending ${payment} to payment done queue");
        this.output.send(MessageBuilder.withPayload(payment).build());
    }
}

package com.ofo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.ofo.domain.Payment;
import com.ofo.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.IOException;

/**
 * Created by XL on 9/4/2017.
 */
@EnableBinding(Sink.class)
@Slf4j
public class PaymentServiceSink {

    @Autowired
    CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void notifyPaymentDone(Payment payment) throws IOException {
        log.info("receiving payment done info from payment-service");
        cartService.updatePaymentDone(payment);
    }
}

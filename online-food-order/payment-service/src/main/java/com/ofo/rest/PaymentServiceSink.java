package com.ofo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofo.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

/**
 * Created by XL on 8/30/2017.
 */

@EnableBinding(Sink.class)
@Slf4j
public class PaymentServiceSink {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void executePayment(String input) throws IOException {
        log.info("Payment input: " + input);
        Payment payload = this.objectMapper.readValue(input, Payment.class);
        this.messagingTemplate.convertAndSend("pay", payload);
        this.messagingTemplate.convertAndSend("topic/payments", payload);

    }
}

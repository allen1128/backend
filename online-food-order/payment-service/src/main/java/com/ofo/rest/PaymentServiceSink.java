package com.ofo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofo.domain.Payment;
import com.ofo.service.PaymentService;
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
    PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void executePayment(String input) throws IOException, InterruptedException {
        log.info("Payment input: " + input);
        Payment payment = this.objectMapper.readValue(input, Payment.class);
        paymentService.process(payment);

        //this.messagingTemplate.convertAndSend("topic/payments", payment);
    }

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void executePayment(Payment payment) throws IOException, InterruptedException {
        paymentService.process(payment);
        //this.messagingTemplate.convertAndSend("topic/payments", payment);
    }
}

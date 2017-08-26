package com.ofo.rest;

import com.ofo.domain.Payment;
import com.ofo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by XL on 8/27/2017.
 */

@RestController
@RequestMapping(name="/api")
public class PaymentRestController {
    @Autowired
    PaymentService paymentService;

    @RequestMapping(name="/paynow", method= RequestMethod.POST)
    public void pay(@RequestBody Payment payment){
        paymentService.save(payment);
    }
}

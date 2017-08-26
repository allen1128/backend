package com.ofo.repository;

import com.ofo.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XL on 8/27/2017.
 */
public interface PaymentRepository extends JpaRepository<Payment, String>{
}

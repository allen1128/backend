package com.ofo.repository;

import com.ofo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by XL on 8/27/2017.
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}

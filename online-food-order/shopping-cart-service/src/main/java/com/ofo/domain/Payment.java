package com.ofo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XL on 8/27/2017.
 */
@Data
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private Long cartId;
    private float total;

    @Temporal(TemporalType.TIMESTAMP)
    private Date initializedAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelledAt;

    public Payment(Cart cart){
        this.cartId = cart.getCartId();
        this.total = cart.getTotal();
    }
}

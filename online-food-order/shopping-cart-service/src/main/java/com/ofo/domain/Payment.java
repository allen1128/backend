package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XL on 8/27/2017.
 */
@Data
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@Entity(name="CART_PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @JsonIgnore
    private Long cartId;
    private float amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt = new Date();

    @Embedded
    @Transient
    @JsonIgnore
    private CreditCard creditCard;

    @JsonCreator
    Payment(float amount){
        this.amount = amount;
    }

    public Payment(CreditCard creditCard, Long cartId, float total) {
        this.creditCard = creditCard;
        this.cartId = cartId;
        this.amount = total;
    }
}

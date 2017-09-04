package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
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
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private Long cartId;
    private String paidBy;
    private float amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private CreditCard creditCard;

    @JsonCreator
    Payment(String paidBy, float amount){
        this.paidBy = paidBy;
        this.amount = amount;
    }
}

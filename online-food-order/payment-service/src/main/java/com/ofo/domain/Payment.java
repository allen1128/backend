package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.javafx.beans.IDProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XL on 8/26/2017.
 */

@Data
@Entity(name="PAYMENT")
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private Long cartId;
    private float amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Embedded
    private CreditCard creditCard;

    @JsonCreator
    Payment(float amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", cartId=" + cartId +
                ", amount=" + amount +
                ", completedAt=" + completedAt +
                ", createdAt=" + createdAt +
                ", creditCard=" + creditCard +
                '}';
    }
}

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
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    @Id
    private String paymentId;
    private String paidBy;
    private float amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paidAt;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonCreator
    Payment(String paidBy, float amount){
        this.paidBy = paidBy;
        this.amount = amount;
    }
}

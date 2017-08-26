package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

/**
 * Created by XL on 8/27/2017.
 */

@Entity
@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Table(name="ONLINE_ORDER")
public class Order {
    @Id
    private String orderId;
    private String ordedBy;

    @Transient
    @JsonProperty
    private float price;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    @ElementCollection
    @CollectionTable(name="ORDER_DISHES", joinColumns = @JoinColumn(name="ORDER_ID"))
    @Column(name="DISH_ID")
    @JsonIgnore
    private Collection<String> dishes;

    @JsonCreator
    public Order(List<String> dishes, String ordedBy) {
        this.dishes = new ArrayList<String>(dishes);
        this.ordedBy = ordedBy;
        this.price = calculatePrice(dishes);
    }

    public float calculatePrice(List<String> dishes) {
        //TODO
        return 0.0f;
    }
}

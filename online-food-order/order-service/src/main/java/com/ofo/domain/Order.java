package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.*;

/**
 * Created by XL on 8/27/2017.
 */

@Entity
@Data
public class Order {
    @Id
    private Long orderId;
    private String orderBy;

    @Transient
    @JsonProperty
    private float price;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ElementCollection
    @CollectionTable(name="ORDER_DISHES", joinColumns = @JoinColumn(name="ORDER_ID"))
    @Column(name="DISH_ID")
    @JsonIgnore
    private Collection<Long> dishes;

    public Order(List<Long> dishes, String orderBy) {
        this.dishes = new ArrayList<Long>(dishes);
        this.orderBy = orderBy;
        this.price = calculatePrice(dishes);
    }

    public float calculatePrice(List<Long> dishes) {
        //TODO
        return 0.0f;
    }
}

package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    private Collection<Long> dishes;

    @JsonCreator
    public Order(List<Long> dishes, String ordedBy) {
        this.dishes = new ArrayList<Long>(dishes);
        this.ordedBy = ordedBy;
        this.price = calculatePrice(dishes);
    }

    public float calculatePrice(List<Long> dishes) {
        //TODO
        return 0.0f;
    }
}

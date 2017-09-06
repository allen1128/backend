package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by XL on 8/27/2017.
 */
@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "RESTAURANT_DISH")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishId;
    private float price;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="RESTAURANT_ID", nullable = false, referencedColumnName = "RESTAURANT_ID")
    @JsonIgnore
    private Restaurant restaurant;

    public Dish(Long dishId) {
        this.dishId = dishId;
    }
}

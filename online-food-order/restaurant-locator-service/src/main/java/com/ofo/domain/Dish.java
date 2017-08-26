package com.ofo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by XL on 8/27/2017.
 */
@Data
@Entity
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Table(name="RESTAURANT_DISH")
public class Dish {
    @Id
    private String dishId;
    private float price;
    private String name;
    private String description;

    public Dish(){
    }

    public Dish(String dishId) {
        this.dishId = dishId;
    }
}

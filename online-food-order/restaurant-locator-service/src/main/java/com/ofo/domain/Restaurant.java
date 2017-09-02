package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.javafx.beans.IDProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Restaurant {

    public enum CuisineType {
        Chinese, JapaneseSushi, Thai, AmercianFastFood, French
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantId;
    private String name;
    private String description;
    private String email;

    @Enumerated(EnumType.STRING)
    private CuisineType cuisineType;


    private float rating;
    private float avgPrice;

    private boolean deliveryEnabled;
    private boolean parkingEnabled;
    private boolean reservationEnabled;

    private Date lastUpdated;
    private Date createdAt;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @JsonCreator
    public Restaurant(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}

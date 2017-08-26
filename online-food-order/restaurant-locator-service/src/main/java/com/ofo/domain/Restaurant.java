package com.ofo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Restaurant {

    private enum CuisineType {
        Chinese, JapaneseSushi, Thai, AmercianFastFood, French
    }

    @Id
    private String restaurantId;
    private String name;
    private String description;

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

    @Embedded
    private Phone phone;

    @OneToMany
    private List<Dish> dishes;
}

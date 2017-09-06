package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.javafx.beans.IDProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name="RESTAURANT_ID")
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

    private Date lastUpdated = new Date();
    private Date createdAt = new Date();

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Dish> menu = new ArrayList<>();

    @JsonCreator
    public Restaurant(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setMenu(List<Dish> menu){
        this.menu = menu;

        for (Dish dish : menu){
            dish.setRestaurant(this);
        }
    }

    public void setCreatedAt(Date createdAt) {
        if (createdAt == null){
            this.createdAt = new Date();
        } else {
            this.createdAt = createdAt;
        }
    }

    public void setLastUpdated(Date lastUpdated) {
        if (lastUpdated == null){
            this.lastUpdated = new Date();
        } else {
            this.lastUpdated = lastUpdated;
        }
    }
}

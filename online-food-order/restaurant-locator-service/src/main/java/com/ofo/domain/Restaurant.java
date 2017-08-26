package com.ofo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.Date;

/**
 * Created by XL on 8/26/2017.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Restaurant {

    private Long restaurantId;
    private String name;
    private String description;
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
}

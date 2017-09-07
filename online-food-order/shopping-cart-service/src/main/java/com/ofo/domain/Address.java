package com.ofo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by XL on 8/26/2017.
 */

@Data
@Embeddable
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@NoArgsConstructor(access= AccessLevel.PUBLIC)
public class Address {
    String apt;
    String street;
    String city;
    String state;
    String country;
    String postalCode;
}

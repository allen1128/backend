package com.ofo.domain;

import javax.persistence.Embeddable;

/**
 * Created by XL on 8/26/2017.
 */

@Embeddable
public class Address {
    String apt;
    String street;
    String city;
    String state;
    String country;
    String postalCode;
}

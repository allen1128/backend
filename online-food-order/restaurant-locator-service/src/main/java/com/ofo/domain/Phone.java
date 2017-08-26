package com.ofo.domain;

import javax.persistence.Embeddable;

/**
 * Created by XL on 8/26/2017.
 */

@Embeddable
public class Phone {
    private int countryCode;
    private int areaCode;
    private int localPhoneNumber;
    private PhoneType type;
}

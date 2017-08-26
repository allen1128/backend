package com.ofo.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by XL on 8/26/2017.
 */

@Embeddable
public class Phone {

    private enum PhoneType {
        mobile, line
    }

    private int countryCode;
    private int areaCode;
    private int localPhoneNumber;

    @Enumerated(EnumType.STRING)
    private PhoneType type;
}

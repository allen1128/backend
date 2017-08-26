package com.ofo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by XL on 8/27/2017.
 */
@Data
@AllArgsConstructor(access=AccessLevel.PUBLIC)
public class Payment {
    private String payBy;
    private float amount;
}

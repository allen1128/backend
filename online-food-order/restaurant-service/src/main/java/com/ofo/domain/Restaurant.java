package com.ofo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by XL on 8/26/2017.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Restaurant {
    private Long id;
    private String name;
    private String description;
}

package com.ofo.domain;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by XL on 8/27/2017.
 */
@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Dish {
    @Id
    private Long dishId;
    private float price;
    private String name;
    private String description;
}

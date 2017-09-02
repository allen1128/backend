package com.ofo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by XL on 8/27/2017.
 */
@Entity
@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PUBLIC)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;
    private float price;
    private String name;
    private int quantity;
}

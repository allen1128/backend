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

    private Long externalItemId;
    private float price;
    private String name;
    private int quantity;

    public CartItem(Long externalItemId, float price, String name, int quantity) {
        this.externalItemId = externalItemId;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public CartItem(Long externalItemId) {
        this.externalItemId = externalItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CartItem cartItem = (CartItem) o;

        if (!externalItemId.equals(cartItem.externalItemId)){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + externalItemId.hashCode();
        return result;
    }
}

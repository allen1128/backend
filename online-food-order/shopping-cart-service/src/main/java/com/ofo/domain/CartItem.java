package com.ofo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by XL on 8/27/2017.
 */
@Embeddable
@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PUBLIC)
public class CartItem {
    private Long externalItemId;
    private float price;
    private String name;
    private int quantity;

    public CartItem(Long externalItemId) {
        this.externalItemId = externalItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return externalItemId.equals(cartItem.externalItemId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + externalItemId.hashCode();
        return result;
    }
}

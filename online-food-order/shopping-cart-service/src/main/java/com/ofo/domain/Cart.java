package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */

@Entity
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    public enum CartType {
        FOOD, MUSIC, SPORTS
    }

    private String orderBy;
    private String note;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private CartType cartType;

    @Transient
    @JsonProperty
    private float total;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt = new Date();

    @ElementCollection
    @CollectionTable(name = "CART_ITEMS", joinColumns = @JoinColumn(name = "CART_ID"))
    @Column(name = "CART_ITEM_ID")
    @JsonIgnore
    private Set<CartItem> cartItems = new HashSet<>();


    public Cart(String orderBy, CartType cartType) {
        this.orderBy = orderBy;
        this.cartType = cartType;
    }

    public float getTotal(Set<CartItem> cartItems) {
        this.total = 0.0f;
        for (CartItem ci : cartItems) {
            this.total += ci.getPrice() * ci.getQuantity();
        }
        return this.total;
    }

    public void updateCartItem(CartItem cartItem){
        for (CartItem ci : cartItems){
            if (ci.equals(cartItem)){
                this.cartItems.remove(ci);
            }
        }

        if (cartItem.getQuantity() > 0) {
            this.cartItems.add(cartItem);
        }
        this.updatedAt = new Date();
    }

    public void remoteCartItem(CartItem cartItem){
        this.cartItems.remove(cartItem);
    }

    public void remoteCartItemById(Long externalItemId) {
        for (CartItem ci : this.cartItems){
            if (ci.getExternalItemId() == externalItemId){
                this.cartItems.remove(ci);
                break;
            }
        }
    }
}

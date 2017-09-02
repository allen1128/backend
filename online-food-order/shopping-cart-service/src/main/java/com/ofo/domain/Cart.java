package com.ofo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by XL on 8/27/2017.
 */

@Entity
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "ONLINE_ORDER")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    private String ordedBy;

    @Transient
    @JsonProperty
    private float total;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    @ElementCollection
    @CollectionTable(name = "ORDER_ITEMS", joinColumns = @JoinColumn(name = "ORDER_ID"))
    @Column(name = "ORDER_ITEM_ID")
    @JsonIgnore
    private Set<CartItem> cartItems;

    public float getTotal(Set<CartItem> cartItems) {
        float total = 0.0f;
        for (CartItem ci : cartItems) {
            total += ci.getPrice() * ci.getQuantity();
        }
        return total;
    }
}

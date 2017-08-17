package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by XL on 8/15/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
@Data
public class User {
    String userName;
    String address;

    public User(){
    }

    public User(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }
}

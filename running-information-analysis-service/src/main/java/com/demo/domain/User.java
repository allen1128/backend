package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by XL on 8/15/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="user_table_generator" )
    @TableGenerator(name="user_table_generator", table="user_keys", pkColumnName = "pk_name", valueColumnName = "pk_value")
    private Long userId;

    private String username;
    private String address;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }
}

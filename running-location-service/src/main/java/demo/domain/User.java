package demo.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created on 8/11/2017.
 */

@Data
@Entity
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;
    private String address;

    public User(){
    }

    public User(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }
}

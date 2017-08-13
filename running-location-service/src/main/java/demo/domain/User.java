package demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * Created on 8/11/2017.
 */

@Data
@Entity
@Table(name="USER")
public class User {
    @Id
    String userName;
    String address;

    public User(){
    }

    public User(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }
}

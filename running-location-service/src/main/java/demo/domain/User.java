package demo.domain;

import javax.persistence.Embedded;


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

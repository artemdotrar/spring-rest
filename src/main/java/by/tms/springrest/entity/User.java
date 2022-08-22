package by.tms.springrest.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private String username;
    private String password;
    private Telephone telephone;
    private Address address;
}

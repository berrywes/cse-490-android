package com.example.berry.helpcustomers.models;

public class User {

    // initialize variables
    private int id;
    private String email, name;

    // constructor method: parameters are response data from API user sub-array
    public User(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // return id from User object
    public int getId() {
        return id;
    }

    // return email from User object
    public String getEmail() {
        return email;
    }

    // return name from User object
    public String getName() {
        return name;
    }
}

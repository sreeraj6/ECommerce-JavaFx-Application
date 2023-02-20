package com.example.ecomm;

public class Customer {

    int id;
    String name;
    String Email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

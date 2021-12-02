package com.example.loginandregister;

public class User {
    private String Phone;
    private String Name;

    public User(){

    }

    public User(String phone,String name) {
        Phone = phone;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}

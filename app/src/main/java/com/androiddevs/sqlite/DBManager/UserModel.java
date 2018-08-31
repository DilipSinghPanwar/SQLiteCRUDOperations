package com.androiddevs.sqlite.DBManager;


public class UserModel {

    /* private variables*/
    private String id;
    private String name;
    private String email;

    /*Empty constructor*/
    public UserModel() {
    }

    /*constructor*/
    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /*constructor*/
    public UserModel(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /*getters & setters*/
     /*getting id*/
    public String getId() {
        return id;
    }

    /*setting id*/
    public void setId(String id) {
        this.id = id;
    }

    /*getting name*/
    public String getName() {
        return name;
    }

    /*setting name*/
    public void setName(String name) {
        this.name = name;
    }

    /*getting email*/
    public String getEmail() {
        return email;
    }

    /*setting email*/
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

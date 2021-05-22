package com.pond.project.service.models;



public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String phoneNumber;
    private int role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }

    public User(String login, String password, String name, String phoneNumber, int role) {
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.name = name;
    }

    public User() {

    }
}

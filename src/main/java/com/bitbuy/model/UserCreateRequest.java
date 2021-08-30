package com.bitbuy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateRequest {

    @JsonProperty("username")
    private String userName = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("phone")
    private String phone = null;

    public UserCreateRequest userName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserCreateRequest password(String password) {
        this.password = password;
        return this;
    }

    public UserCreateRequest name(String name) {
        this.name = name;
        return this;
    }

    public UserCreateRequest email(String email) {
        this.email = email;
        return this;
    }

    public UserCreateRequest phone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

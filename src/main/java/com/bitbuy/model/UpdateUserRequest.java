package com.bitbuy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserRequest {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("phone")
    private String phone = null;

    public UpdateUserRequest name(String name) {
        this.name = name;
        return this;
    }

    public UpdateUserRequest email(String email) {
        this.email = email;
        return this;
    }

    public UpdateUserRequest phone(String phone) {
        this.phone = phone;
        return this;
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

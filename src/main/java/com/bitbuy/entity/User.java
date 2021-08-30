package com.bitbuy.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(
                name="userByUserName",
                query="select u from User u where u.userName=:userName"),
        @NamedQuery(
                name="userById",
                query="select u from User u where u.id = :id"
        )

})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "uuid", unique = true)
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    private String name;
    private String email;
    private String phone;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

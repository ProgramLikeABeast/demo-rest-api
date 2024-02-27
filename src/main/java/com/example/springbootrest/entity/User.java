package com.example.springbootrest.entity;

import jakarta.persistence.*;


@Entity
@Table(name="users")
public class User {

    public User() {
    }
    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    @Column(name="phone")
    private String phone;
    @Column(name="username")
    private String username;
    @Column(name="pwd")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="momo_coins")
    private int momo_coins;

    public int getUid() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMomo_coins() {
        return momo_coins;
    }

    public void setMomo_coins(int momo_coins) {
        this.momo_coins = momo_coins;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + uid +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", momo_coins=" + momo_coins +
                '}';
    }
}

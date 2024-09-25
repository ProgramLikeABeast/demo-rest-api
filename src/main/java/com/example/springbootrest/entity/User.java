package com.example.springbootrest.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name="users")
public class User {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Lazy
    private int uid;
    @Column(name="phone")
    private String phone;
    @Column(name="username")
    private String username;
    @Column(name="pwd")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="momo_stamp")
    private int momo_stamp;
    @Column(name="award")
    private int award;
    @Column(name="balance")
    private double balance;
    @Column(name="verified")
    private boolean verified;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

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

    public int getMomo_stamp() {
        return momo_stamp;
    }

    public void setMomo_stamp(int momo_coins) {
        this.momo_stamp = momo_coins;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", momo_stamp=" + momo_stamp +
                ", award=" + award +
                ", balance=" + balance +
                '}';
    }

}



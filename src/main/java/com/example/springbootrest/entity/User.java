package com.example.springbootrest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="users")
public class User {

    public User() {
    }
    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Column(name="username")
    @Getter @Setter
    private String username;
    @Column(name="pwd")
    @Getter @Setter
    private String password;
    @Column(name="email")
    @Getter @Setter
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

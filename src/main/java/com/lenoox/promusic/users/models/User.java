package com.lenoox.promusic.users.models;


import com.lenoox.promusic.common.models.Auditable;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "password")
    private String password;
    @Column(name = "last_login")
    private String lastLogin;
    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @Column(name = "active")
    private Boolean active;
}
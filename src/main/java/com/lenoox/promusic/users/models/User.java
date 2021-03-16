package com.lenoox.promusic.users.models;


import com.lenoox.promusic.common.models.Auditable;
import com.lenoox.promusic.users.dtos.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AuditorAware;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "active")
    private Boolean active;

    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    private Role roles;
    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =  @JoinColumn(name ="user_id"),inverseJoinColumns= @JoinColumn(name="role_id"))
    private Set<Role> roles;
*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public UserDto toUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setEmail(this.email);
        userDto.setFirstName(this.firstName);
        userDto.setLastName(this.lastName);
        userDto.setRole(this.roles.getName().toString());
        return userDto;
    }
}
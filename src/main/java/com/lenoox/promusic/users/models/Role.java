package com.lenoox.promusic.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lenoox.promusic.common.models.RoleType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleType name;
    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<User> users;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

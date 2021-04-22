package com.lenoox.promusic.users.models;

import com.lenoox.promusic.common.models.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleType name;
    @OneToMany(mappedBy="role",fetch=FetchType.EAGER)
    private Set<User> users;
}

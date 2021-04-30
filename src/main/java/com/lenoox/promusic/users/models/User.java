package com.lenoox.promusic.users.models;


import com.lenoox.promusic.common.models.AuditableTime;
import com.lenoox.promusic.common.utils.EncryptedStringConverter;
import com.lenoox.promusic.orders.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditableTime implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Convert(converter = EncryptedStringConverter.class)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String username;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "password")
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    private Role role;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy="employee")
    private Set<Order> orderEmployee;
    @OneToMany(mappedBy="client")
    private Set<Order> orderClient;
}

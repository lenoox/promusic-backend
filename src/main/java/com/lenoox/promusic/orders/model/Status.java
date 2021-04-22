package com.lenoox.promusic.orders.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_status")
public class Status  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private Long id;
    @Column(name = "status_name")
    private String name;
    @OneToMany(mappedBy="status")
    private Set<Order> order;
}

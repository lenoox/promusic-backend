package com.lenoox.promusic.orders.model;

import com.lenoox.promusic.common.models.AuditableTime;
import com.lenoox.promusic.common.models.ProductOrder;
import com.lenoox.promusic.users.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AuditableTime implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    @Column(name = "order_note")
    private String note;
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private User client;
    @ManyToOne
    @JoinColumn(name="order_status_id", nullable=false)
    private Status status;
    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private User employee;
    @Column(name = "grand_total")
    private BigDecimal grandTotal;
    @OneToMany(mappedBy = "order")
    Set<ProductOrder> productOrder;
}

package com.lenoox.promusic.orders.model;

import com.lenoox.promusic.common.models.Auditable;
import com.lenoox.promusic.orders.model.Status;
import com.lenoox.promusic.users.models.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orders")
public class Order extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    @Column(name = "order_note")
    private String note;
/*
    @Column(name = "client_id")
    private User client;
    @Column(name = "order_status_id")
    private Status status;
    @Column(name = "employee_id")
    private User employee;
*/
    @Column(name = "grand_total")
    private BigDecimal grandTotal;
}

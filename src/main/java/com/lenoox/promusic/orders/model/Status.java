package com.lenoox.promusic.orders.model;

import com.lenoox.promusic.common.models.Auditable;
import com.lenoox.promusic.users.models.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orders")
public class Status extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private long id;
    @Column(name = "status_name")
    private String note;
}

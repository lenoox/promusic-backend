package com.lenoox.promusic.common.models;

import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.products.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orders")
public class ProductOrder extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private long id;
    @Column(name = "quantity")
    private String quantity;
/*    @Column(name = "product_id")
    private Product product;
    @Column(name = "order_id")
    private Order order;*/
}

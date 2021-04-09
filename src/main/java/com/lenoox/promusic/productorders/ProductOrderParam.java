package com.lenoox.promusic.productorders;

import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.products.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderParam {
    private int quantity;
    private Product product;
    //private Order order;
}

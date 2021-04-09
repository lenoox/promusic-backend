package com.lenoox.promusic.productorders;

import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.products.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderDto {
    private long id;
    private int quantity;
    private ProductDto product;
    //private OrderDto order;
}

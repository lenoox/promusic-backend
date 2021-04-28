package com.lenoox.promusic.productorders;

import com.lenoox.promusic.orders.param.OrderParam;
import com.lenoox.promusic.products.ProductParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderParam {
    private Long id;
    private int quantity;
    private ProductParam product;
    private OrderParam order;
}

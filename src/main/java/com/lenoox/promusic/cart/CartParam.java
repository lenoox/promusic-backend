package com.lenoox.promusic.cart;

import com.lenoox.promusic.productorders.ProductOrderParam;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class CartParam {
    private BigDecimal grandTotal;
    private Set<ProductOrderParam> productOrder;
}

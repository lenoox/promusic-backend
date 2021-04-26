package com.lenoox.promusic.cart;

import com.lenoox.promusic.productorders.ProductOrderDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class CartDto {
    private BigDecimal grandTotal;
    private Set<ProductOrderDto> productOrder;
}

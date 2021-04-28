package com.lenoox.promusic.orders.param;

import com.lenoox.promusic.productorders.ProductOrderParam;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class OrderParam {
    private Long id;
    private String note;
    private StatusParam status;
    private BigDecimal grandTotal;
    private Set<ProductOrderParam> productOrder;
}

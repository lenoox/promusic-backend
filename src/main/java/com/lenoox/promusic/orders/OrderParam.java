package com.lenoox.promusic.orders;


import com.lenoox.promusic.orders.model.Status;
import com.lenoox.promusic.productorders.ProductOrderParam;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.models.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderParam {
    private Long id;
    private String note;
    private UserParam client;
    private StatusParam status;
    private UserParam employee;
    private BigDecimal grandTotal;
    private List<ProductOrderParam> productOrder;
}

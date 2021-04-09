package com.lenoox.promusic.orders;

import com.lenoox.promusic.orders.dtos.StatusDto;

import com.lenoox.promusic.productorders.ProductOrderParam;
import com.lenoox.promusic.users.models.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderParam {
    private long id;
    private String note;
    private User client;
    private StatusDto status;
    private User employee;
    private BigDecimal grandTotal;
    private List<ProductOrderParam> productOrder;
}

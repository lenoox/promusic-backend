package com.lenoox.promusic.orders.dtos;

import com.lenoox.promusic.productorders.ProductOrderDto;
import com.lenoox.promusic.users.dtos.UserDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private String note;
    private UserDto client;
    private StatusDto status;
    private UserDto employee;
    private BigDecimal grandTotal;
    private Set<ProductOrderDto> productOrder;
}

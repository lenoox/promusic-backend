package com.lenoox.promusic.orders.mapper;

import com.lenoox.promusic.orders.param.OrderParam;
import com.lenoox.promusic.orders.param.OrderStatusParam;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusMapper {

    public OrderParam orderStatusParamToOrderParam(OrderStatusParam orderStatusParam) {
        OrderParam orderParam = new OrderParam();
        orderParam.setStatus(orderStatusParam.getStatus());
        return orderParam;
    }
}

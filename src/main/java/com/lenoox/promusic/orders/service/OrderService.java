package com.lenoox.promusic.orders.service;

import com.lenoox.promusic.orders.param.OrderParam;
import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.common.dtos.PageDto;
import com.lenoox.promusic.orders.param.OrderStatusParam;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    PageDto<OrderDto> getAll(Pageable paging);
    OrderDto getById(Long id);
    OrderDto create(OrderParam order);
    OrderDto changeStatusByOrder(Long id, OrderStatusParam statusParam);
    void delete(Long id);
}

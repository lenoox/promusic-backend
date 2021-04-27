package com.lenoox.promusic.orders;

import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.common.dtos.PageDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    PageDto<OrderDto> getAll(Pageable paging);
    OrderDto getById(Long id);
    OrderDto create(OrderParam order);
    OrderDto update(Long id, OrderParam order);
    void delete(Long id);
}

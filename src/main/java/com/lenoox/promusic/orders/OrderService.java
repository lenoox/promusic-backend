package com.lenoox.promusic.orders;

import com.lenoox.promusic.orders.dtos.OrderDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll(Pageable paging);
    OrderDto getById(Long id);
    OrderDto create(OrderParam order);
    OrderDto update(Long id, OrderParam order);
    void delete(long id);
}

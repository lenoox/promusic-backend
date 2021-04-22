package com.lenoox.promusic.orders.mapper;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import com.lenoox.promusic.orders.OrderParam;
import com.lenoox.promusic.orders.StatusRepository;
import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.orders.dtos.StatusDto;
import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.orders.model.Status;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.mapper.UserMapper;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public OrderMapper(StatusRepository statusRepository, StatusMapper statusMapper, UserMapper userMapper, UserRepository userRepository) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public Order paramToEntity(OrderParam orderParam) {
        Order order = new Order();
        order.setNote(orderParam.getNote());
        User client = userRepository.findById(orderParam.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(orderParam.getClient().getId()));
        order.setClient(client);
        Status status = statusRepository.findById(orderParam.getStatus().getId())
                .orElseThrow(() -> new ResourceNotFoundException(orderParam.getStatus().getId()));
        order.setStatus(status);
        User employee = userRepository.findById(orderParam.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(orderParam.getEmployee().getId()));
        order.setEmployee(employee);
        order.setGrandTotal(orderParam.getGrandTotal());
        return order;
    }

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setNote(order.getNote());
        UserDto client = userMapper.entityToDto(order.getClient());
        orderDto.setClient(client);
        StatusDto status = statusMapper.entityToDto(order.getStatus());
        orderDto.setStatus(status);
        UserDto employee = userMapper.entityToDto(order.getEmployee());
        orderDto.setClient(employee);
        orderDto.setGrandTotal(order.getGrandTotal());
        return orderDto;
    }
}

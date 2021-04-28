package com.lenoox.promusic.orders.mapper;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import com.lenoox.promusic.common.exception.UserNotFoundException;
import com.lenoox.promusic.orders.param.OrderParam;
import com.lenoox.promusic.orders.repository.OrderRepository;
import com.lenoox.promusic.orders.repository.StatusRepository;
import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.orders.dtos.StatusDto;
import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.orders.model.Status;
import com.lenoox.promusic.productorders.ProductOrder;
import com.lenoox.promusic.productorders.ProductOrderDto;
import com.lenoox.promusic.productorders.ProductOrderMapper;
import com.lenoox.promusic.productorders.ProductOrderParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.mapper.UserMapper;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@Log
@Component
public class OrderMapper {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ProductOrderMapper productOrderMapper;
    private final OrderRepository orderRepository;

    public OrderMapper(StatusRepository statusRepository, StatusMapper statusMapper, UserMapper userMapper, UserRepository userRepository, ProductOrderMapper productOrderMapper, OrderRepository orderRepository) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.productOrderMapper = productOrderMapper;
        this.orderRepository = orderRepository;
    }

    public Order paramToEntity(OrderParam orderParam, String clientEmail, String employeeEmail) {
        Order order = new Order();
        Order orderSaved = null;
        User employeeUser = null;
        User clientUser = null;
        Status status = null;
        if(orderParam.getId()!=null){
            orderSaved = orderRepository.findById(orderParam.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(orderParam.getId()));
            order.setId(orderSaved.getId());
            order.setNote(orderSaved.getNote());
            order.setCreatedDate(orderSaved.getCreatedDate());
            Order finalOrderSaved = orderSaved;
            clientUser = userRepository.findByUsername(orderSaved.getClient().getUsername())
                    .orElseThrow(() -> new UserNotFoundException(finalOrderSaved.getClient().getUsername()));
            status = statusRepository.findById(orderParam.getStatus().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(orderParam.getStatus().getId()));
            log.info(employeeEmail);
            employeeUser = userRepository.findByUsername(employeeEmail)
                    .orElseThrow(() -> new UserNotFoundException(employeeEmail));
            order.setGrandTotal(orderSaved.getGrandTotal());
            order.setProductOrder(orderSaved.getProductOrder());
        } else{
            order.setNote(orderParam.getNote());
            clientUser = userRepository.findByUsername(clientEmail)
                    .orElseThrow(() -> new UserNotFoundException(clientEmail));
            status = statusRepository.findById(Long.valueOf(1))
                    .orElseThrow(() -> new ResourceNotFoundException(Long.valueOf(1)));
            BigDecimal grandTotal = new BigDecimal(0);
            for(ProductOrderParam orderProductElement : orderParam.getProductOrder()){
                ProductOrder productOrder = productOrderMapper.paramToEntity(orderProductElement,order);
                BigDecimal b1 =  new BigDecimal(productOrder.getQuantity());
                BigDecimal b2 =  productOrder.getProduct().getPrice();
                BigDecimal grandCurrent = b1.multiply(b2);
                grandTotal = grandTotal.add(grandCurrent);
            }
            order.setGrandTotal(grandTotal);
            Set<ProductOrder> productOrders = new HashSet<>();
            for(ProductOrderParam orderProductElement : orderParam.getProductOrder()){
                ProductOrder productOrder = productOrderMapper.paramToEntity(orderProductElement,order);
                productOrders.add(productOrder);
            }
            order.setProductOrder(productOrders);
        }
        order.setClient(clientUser);
        order.setStatus(status);
        order.setEmployee(employeeUser);

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
        if(order.getEmployee()!=null){
            UserDto employee = userMapper.entityToDto(order.getEmployee());
            orderDto.setEmployee(employee);
        }
        orderDto.setGrandTotal(order.getGrandTotal());
        Set<ProductOrderDto> productOrderList = new HashSet<>();
        for(ProductOrder productOrderElement : order.getProductOrder()){
            ProductOrderDto productOrderDto = productOrderMapper.entityToDto(productOrderElement);
            productOrderList.add(productOrderDto);
        }
        orderDto.setProductOrder(productOrderList);
        return orderDto;
    }
}

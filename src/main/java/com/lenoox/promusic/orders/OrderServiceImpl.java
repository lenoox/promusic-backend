package com.lenoox.promusic.orders;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.orders.mapper.OrderMapper;
import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.productorders.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final OrderMapper orderMapper;
    private final ProductOrderMapper productOrderMapper;
    private EntityManager em;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductOrderRepository productOrderRepository,
                            OrderMapper orderMapper,
                            ProductOrderMapper productOrderMapper,
                            EntityManager em) {
        this.orderRepository = orderRepository;
        this.productOrderRepository = productOrderRepository;
        this.orderMapper = orderMapper;
        this.productOrderMapper = productOrderMapper;
        this.em = em;
    }


    @Override
    public List<OrderDto> getAll(Pageable paging) {
        return orderRepository
                .findAll(paging)
                .stream()
                .map(order -> orderMapper.entityToDto(order))
                .collect(Collectors.toList());
    }
    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        OrderDto orderDto = orderMapper.entityToDto(order);
        return orderDto;
    }

    @Override
    public OrderDto create(OrderParam orderParam) {
        Order order = orderMapper.paramToEntity(orderParam);
        Order orderSaved = orderRepository.save(order);
        em.refresh(orderSaved);

        List<ProductOrder> productOrders = new ArrayList<>();
        for(ProductOrderParam orderProductElement : orderParam.getProductOrder()){
            ProductOrder productOrder = productOrderMapper.paramToEntity(orderProductElement, orderSaved);
            productOrders.add(productOrder);
        }
        List<ProductOrder> productOrderSaved = productOrderRepository.saveAll(productOrders);
        for(ProductOrder orderProductElement : productOrderSaved) {
            em.refresh(orderProductElement);
        }

        OrderDto orderDto = orderMapper.entityToDto(orderSaved);
        List<ProductOrderDto> productOrderList = new ArrayList<>();
        for(ProductOrder productOrderElement : productOrderSaved){
            ProductOrderDto productOrderDto = productOrderMapper.entityToDto(productOrderElement);
            productOrderList.add(productOrderDto);
        }
        orderDto.setProductOrder(productOrderList);

        return orderDto;
    }
    @Override
    public OrderDto update(Long id, OrderParam orderParam) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        Order order = orderMapper.paramToEntity(orderParam);
        order.setId(id);
        Order orderSaved = orderRepository.save(order);
        OrderDto orderDto = orderMapper.entityToDto(orderSaved);
        return orderDto;
    }
    @Override
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        orderRepository.deleteById(id);
    }
}

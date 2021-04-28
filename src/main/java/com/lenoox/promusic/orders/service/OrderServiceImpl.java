package com.lenoox.promusic.orders.service;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import com.lenoox.promusic.common.services.UserDetailsImpl;
import com.lenoox.promusic.orders.mapper.OrderStatusMapper;
import com.lenoox.promusic.orders.param.OrderParam;
import com.lenoox.promusic.orders.param.OrderStatusParam;
import com.lenoox.promusic.orders.repository.OrderRepository;
import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.common.dtos.PageDto;
import com.lenoox.promusic.orders.mapper.OrderMapper;
import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.users.service.AuthenticationFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.stream.Collectors;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final EntityManager em;
    private final AuthenticationFacadeService authenticationFacadeService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderStatusMapper orderStatusMapper;


    public OrderServiceImpl(EntityManager em,
                            AuthenticationFacadeService authenticationFacadeService,
                            OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            OrderStatusMapper orderStatusMapper) {
        this.em = em;
        this.authenticationFacadeService = authenticationFacadeService;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderStatusMapper = orderStatusMapper;
    }
    @Override
    public PageDto<OrderDto> getAll(Pageable paging) {
        Page<Order> orderPage = orderRepository.findAll(paging);
        return new PageDto(orderPage.getContent()
                .stream()
                .map(order -> orderMapper.entityToDto(order))
                .collect(Collectors.toList()), orderPage.getTotalElements());
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
        Object principal = authenticationFacadeService.getAuthentication().getPrincipal();
        String clientEmail = ((UserDetailsImpl) principal).getUser().getUsername();
        Order order = orderMapper.paramToEntity(orderParam,clientEmail,null);
        Order orderSaved = orderRepository.save(order);
        em.refresh(orderSaved);
        log.info("client {} create order",clientEmail);
        OrderDto orderDto = orderMapper.entityToDto(orderSaved);
        return orderDto;
    }
    @Override
    public OrderDto changeStatusByOrder(Long id, OrderStatusParam statusParam) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        Object principal = authenticationFacadeService.getAuthentication().getPrincipal();
        String employeeEmail = ((UserDetailsImpl) principal).getUser().getUsername();
        OrderParam orderParam = orderStatusMapper.orderStatusParamToOrderParam(statusParam);
        orderParam.setId(id);
        Order order = orderMapper.paramToEntity(orderParam, null, employeeEmail);
        Order orderSaved = orderRepository.save(order);
        log.info("employee {} updated status in order", employeeEmail);
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

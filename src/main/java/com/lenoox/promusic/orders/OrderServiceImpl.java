package com.lenoox.promusic.orders;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.orders.model.Status;
import com.lenoox.promusic.productorders.ProductOrder;
import com.lenoox.promusic.productorders.ProductOrderDto;
import com.lenoox.promusic.productorders.ProductOrderParam;
import com.lenoox.promusic.productorders.ProductOrderRepository;
import com.lenoox.promusic.products.Product;
import com.lenoox.promusic.products.ProductDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EntityManager em;

    @Override
    public List<OrderDto> getAll(Pageable paging) {
        return orderRepository
                .findAll(paging)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id).get();
        OrderDto orderDto = new OrderDto();
        modelMapper.map(order, orderDto);
        return orderDto;
    }

    @Override
    public OrderDto create(OrderParam orderParam) {
        Order order = new Order();
        order.setClient(orderParam.getClient());
        order.setEmployee(orderParam.getEmployee());
        order.setGrandTotal(orderParam.getGrandTotal());
        order.setNote(orderParam.getNote());
        Status status = new Status();
        status.setId(orderParam.getStatus().getId());
        status.setNote(orderParam.getStatus().getNote());
        order.setStatus(status);
//        Order orderSaved = orderRepository.save(order);
//        em.refresh(orderSaved);

        List<ProductOrder> productOrders = new ArrayList<>();
        for(ProductOrderParam orderProductElement : orderParam.getProductOrder()){
            Product product = new Product();
            ProductOrder productOrder = new ProductOrder();
            modelMapper.map(orderProductElement.getProduct(), product);
//            productOrder.setOrder(order);
            productOrder.setProduct(product);
            productOrder.setQuantity(orderProductElement.getQuantity());
            productOrders.add(productOrder);
        }
//        List<ProductOrder> productOrderSaved = productOrderRepository.saveAll(productOrders);
        order.setProductOrder(new HashSet<>(productOrders));
/*        for(ProductOrder orderProductElement : productOrderSaved) {
            em.refresh(orderProductElement);
        }*/

        Order orderSaved = orderRepository.save(order);
        em.refresh(orderSaved);

        OrderDto orderDto = new OrderDto();
        modelMapper.map(orderSaved, orderDto);
        List<ProductOrderDto> productOrderList = new ArrayList<>();
        for(ProductOrder productOrderElement : orderSaved.getProductOrder()){
            ProductDto productDto = new ProductDto();
            modelMapper.map(productOrderElement.getProduct(), productDto);
            ProductOrderDto productOrderDto = new ProductOrderDto();
            productOrderDto.setId(productOrderElement.getId());
            productOrderDto.setQuantity(productOrderElement.getQuantity());
            productOrderDto.setProduct(productDto);
            productOrderList.add(productOrderDto);
        }
        orderDto.setProductOrder(productOrderList);

        return orderDto;
    }
    @Override
    public OrderDto update(Long id, OrderParam orderParam) {
        Order order= orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

        modelMapper.map(orderParam, order);
        orderRepository.save(order);
        OrderDto orderDto = new OrderDto();
        modelMapper.map(order, orderDto);
        return orderDto;
    }
    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
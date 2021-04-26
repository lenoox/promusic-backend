package com.lenoox.promusic.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "cartService")
public class CartServiceImpl implements CartService {

    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartMapper cartMapper;

    @Override
    public CartDto getByIds(CartParam cartParam) {
        CartDto orderDto = cartMapper.entityToDto(cartParam);
        return orderDto;
    }
}

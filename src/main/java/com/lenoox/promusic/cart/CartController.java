package com.lenoox.promusic.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService productService;

    @PostMapping()
    public ResponseEntity<CartDto> getProductFromCart(@RequestBody CartParam cartParam){
        return ResponseEntity.ok().body(productService.getByIds(cartParam));
    }
}

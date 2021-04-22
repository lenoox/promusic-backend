package com.lenoox.promusic.orders;

import com.lenoox.promusic.orders.dtos.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService brandService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(brandService.getAll(pageable));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto>  getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(brandService.getById(id));
    }
    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderParam brand){
        return ResponseEntity.ok().body(brandService.create(brand));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable(value = "id") Long id,
                                           @RequestBody OrderParam brand){
        return ResponseEntity.ok().body(brandService.update(id,brand));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        brandService.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}
package com.lenoox.promusic.orders;

import com.lenoox.promusic.orders.dtos.OrderDto;
import com.lenoox.promusic.common.dtos.PageDto;
import com.lenoox.promusic.orders.param.OrderParam;
import com.lenoox.promusic.orders.param.OrderStatusParam;
import com.lenoox.promusic.orders.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService brandService;

    @GetMapping
    public ResponseEntity<PageDto<OrderDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
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
    @PutMapping(value = "/{id}/status")
    public ResponseEntity<OrderDto> changeStatusByOrder(@PathVariable(value = "id") Long id,
                                                        @RequestBody OrderStatusParam orderStatusParam){
        return ResponseEntity.ok().body(brandService.changeStatusByOrder(id, orderStatusParam));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        brandService.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}

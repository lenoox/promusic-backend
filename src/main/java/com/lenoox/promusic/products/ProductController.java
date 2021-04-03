package com.lenoox.promusic.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(productService.getAll(pageable));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(productService.getById(id));
    }
    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductParam product){
        return ResponseEntity.ok().body(productService.create(product));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable(value = "id") Long id,
                                           @RequestBody ProductParam product){
        return ResponseEntity.ok().body(productService.update(id,product));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        productService.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}
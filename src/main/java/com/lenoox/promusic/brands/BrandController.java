package com.lenoox.promusic.brands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(brandService.getAll(pageable));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<BrandDto>  getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(brandService.getById(id));
    }
    @PostMapping
    public ResponseEntity<BrandDto> create(@RequestBody BrandParam brand){
        return ResponseEntity.ok().body(brandService.create(brand));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<BrandDto> update(@PathVariable(value = "id") Long id,
                              @RequestBody BrandParam brand){
        return ResponseEntity.ok().body(brandService.update(id,brand));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        brandService.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}

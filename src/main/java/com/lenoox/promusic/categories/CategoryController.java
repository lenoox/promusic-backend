package com.lenoox.promusic.categories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(@PageableDefault(sort = "slug", page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(categoryService.getAll(pageable));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto>  getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(categoryService.getById(id));
    }
    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryParam category){
        return ResponseEntity.ok().body(categoryService.create(category));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable(value = "id") Long id,
                              @RequestBody CategoryParam category){
        return ResponseEntity.ok().body(categoryService.update(id,category));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        categoryService.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}
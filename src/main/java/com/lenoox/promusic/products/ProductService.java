package com.lenoox.promusic.products;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDto> getByCategory(String category, Pageable paging);
    ProductDto getById(Long id);
    ProductDto create(ProductParam product);
    ProductDto update(Long id, ProductParam product);
    void delete(long id);
}

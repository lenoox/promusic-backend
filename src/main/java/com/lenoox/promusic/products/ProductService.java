package com.lenoox.promusic.products;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDto> getByCategory(String category, Pageable paging);
    ProductDto getById(Long id);
    List<ProductDto> getByIds(List<Long> ids);
    ProductDto create(ProductParam product);
    ProductDto update(Long id, ProductParam product);
    void delete(Long id);
}

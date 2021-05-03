package com.lenoox.promusic.products;

import com.lenoox.promusic.common.dtos.PageDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    PageDto<ProductDto> getByCategory(String category, Pageable paging);
    PageDto<ProductDto> getAll(Pageable paging);
    ProductDto getById(Long id);
    ProductDto create(ProductParam product);
    ProductDto update(Long id, ProductParam product);
    void delete(Long id);
}

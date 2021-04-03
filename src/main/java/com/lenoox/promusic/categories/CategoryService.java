package com.lenoox.promusic.categories;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll(Pageable paging);
    CategoryDto getById(Long id);
    CategoryDto create(CategoryParam brand);
    CategoryDto update(Long id, CategoryParam brand);
    void delete(long id);
}

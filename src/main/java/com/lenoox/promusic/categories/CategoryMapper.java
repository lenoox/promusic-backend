package com.lenoox.promusic.categories;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category paramToEntity(CategoryParam brandParam) {
        Category brand = new Category();
        brand.setName(brandParam.getName());
        brand.setSlug();
        return brand;
    }

    public CategoryDto entityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setSlug(category.getSlug());
        return categoryDto;
    }
}

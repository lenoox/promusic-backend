package com.lenoox.promusic.products;

import com.lenoox.promusic.brands.Brand;
import com.lenoox.promusic.brands.BrandDto;
import com.lenoox.promusic.brands.BrandMapper;
import com.lenoox.promusic.brands.BrandRepository;
import com.lenoox.promusic.categories.Category;
import com.lenoox.promusic.categories.CategoryDto;
import com.lenoox.promusic.categories.CategoryMapper;
import com.lenoox.promusic.categories.CategoryRepository;
import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public ProductMapper(
            CategoryRepository categoryRepository,
            BrandRepository brandRepository,
            CategoryMapper categoryMapper,
            BrandMapper brandMapper) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.categoryMapper = categoryMapper;
        this.brandMapper = brandMapper;
    }

    public Product paramToEntity(ProductParam productParam) {

        Product product = new Product();
        product.setName(productParam.getName());
        product.setSlug(productParam.getSlug());
        product.setQuantity(productParam.getQuantity());
        product.setPrice(productParam.getPrice());
        product.setDescription(productParam.getDescription());
        product.setThumbnail(productParam.getThumbnail());
        Category category = categoryRepository.findById(productParam.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException(productParam.getCategory().getId()));
        product.setCategory(category);
        Brand brand = brandRepository.findById(productParam.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException(productParam.getBrand().getId()));
        product.setBrand(brand);
        product.setEanCode(productParam.getEanCode());
        return product;
    }

    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setSlug(product.getSlug());
        productDto.setQuantity(product.getQuantity());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setThumbnail(product.getThumbnail());
        CategoryDto categoryDto = categoryMapper.entityToDto(product.getCategory());
        productDto.setCategory(categoryDto);
        BrandDto brandDto = brandMapper.entityToDto(product.getBrand());
        productDto.setBrand(brandDto);
        productDto.setEanCode(product.getEanCode());
        return productDto;
    }
}

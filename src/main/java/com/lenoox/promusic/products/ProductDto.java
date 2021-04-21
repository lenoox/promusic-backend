package com.lenoox.promusic.products;

import com.lenoox.promusic.brands.BrandDto;
import com.lenoox.promusic.categories.CategoryDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String slug;
    private int quantity;
    private BigDecimal price;
    private String description;
    private String thumbnail;
    private CategoryDto category;
    private BrandDto brand;
    private String eanCode;
}

package com.lenoox.promusic.products;



import com.lenoox.promusic.brands.BrandParam;
import com.lenoox.promusic.categories.CategoryParam;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductParam {
    private Long id;
    private String name;
    private String slug;
    private int quantity;
    private BigDecimal price;
    private String description;
    private String thumbnail;
    private CategoryParam category;
    private BrandParam brand;
    private String eanCode;
}

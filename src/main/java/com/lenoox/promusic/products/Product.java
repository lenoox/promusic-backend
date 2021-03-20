package com.lenoox.promusic.products;

import com.lenoox.promusic.brands.Brand;
import com.lenoox.promusic.categories.Category;
import com.lenoox.promusic.common.models.Auditable;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;
    @Column(name = "product_name")
    private String name;
/*    @Column(name = "slug")
    private String slug;*/
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "description")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
/*    @Column(name = "category_id")
    private Category category;
    @Column(name = "brand_id")
    private Brand brand_id;*/
    @Column(name = "ean_code")
    private String ean_code;
}

package com.lenoox.promusic.products;

import com.lenoox.promusic.brands.Brand;
import com.lenoox.promusic.categories.Category;
import com.lenoox.promusic.common.models.Auditable;
import com.lenoox.promusic.common.models.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "slug")
    private String slug;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "description")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
    @ManyToOne
    @JoinColumn(name="brand_id", nullable=false)
    private Brand brand;
    @Column(name = "ean_code")
    private String ean_code;
    @OneToMany(mappedBy = "product")
    Set<ProductOrder> productOrder;
}

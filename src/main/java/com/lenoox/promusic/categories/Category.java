package com.lenoox.promusic.categories;

import com.lenoox.promusic.products.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;
    @Column(name = "category_name")
    private String name;
    @Column(name = "slug")
    private String slug;
    @OneToMany(mappedBy="category")
    private Set<Product> product;
}

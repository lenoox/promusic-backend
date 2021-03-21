package com.lenoox.promusic.brands;

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
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private long id;
    @Column(name = "brand_name")
    private String name;
    @OneToMany(mappedBy="brand")
    private Set<Product> product;
}

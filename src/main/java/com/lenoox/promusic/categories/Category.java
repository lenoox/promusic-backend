package com.lenoox.promusic.categories;

import com.github.slugify.Slugify;
import com.lenoox.promusic.products.Product;
import lombok.AccessLevel;
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
    private Long id;
    @Column(name = "category_name")
    private String name;
    @Setter(AccessLevel.NONE)
    @Column(name = "slug")
    private String slug;
    @OneToMany(mappedBy="category")
    private Set<Product> product;

    public void setSlug() {
        if(this.name != null && !this.name.isEmpty()){
            Slugify slg = new Slugify();
            this.slug = slg.slugify(this.name);
        }
    }
}

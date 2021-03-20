package com.lenoox.promusic.categories;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;
    @Column(name = "category_name")
    private String name;
/*    @Column(name = "slug")
    private String slug;*/
}

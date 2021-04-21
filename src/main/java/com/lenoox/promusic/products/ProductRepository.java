package com.lenoox.promusic.products;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_slug(String slug, Pageable paging);

    @Query("SELECT p FROM Product p WHERE p.id IN(:ids)")
    List<Product> getAllProducts(@Param("ids") List<Long> ids);
}

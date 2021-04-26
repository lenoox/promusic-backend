package com.lenoox.promusic.cart;

import com.lenoox.promusic.productorders.ProductOrder;
import com.lenoox.promusic.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<ProductOrder, Long> {

    @Query("SELECT p FROM Product p WHERE p.id IN(:ids)")
    List<Product> getAllProducts(@Param("ids") List<Long> ids);
}

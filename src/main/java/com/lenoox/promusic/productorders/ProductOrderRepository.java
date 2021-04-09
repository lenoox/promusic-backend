package com.lenoox.promusic.productorders;

import com.lenoox.promusic.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}

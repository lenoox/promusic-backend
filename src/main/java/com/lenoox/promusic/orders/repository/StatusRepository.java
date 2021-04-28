package com.lenoox.promusic.orders.repository;

import com.lenoox.promusic.orders.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}

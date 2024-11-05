package com.project.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.product.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

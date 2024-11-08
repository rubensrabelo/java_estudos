package com.project.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.product.models.OrderItem;
import com.project.product.models.pk.OrderItemPK;

public interface OrdemItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}

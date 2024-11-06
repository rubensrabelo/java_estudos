package com.project.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.product.models.Product;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {
}

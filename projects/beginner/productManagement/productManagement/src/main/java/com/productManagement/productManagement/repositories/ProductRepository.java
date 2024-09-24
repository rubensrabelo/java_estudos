package com.productManagement.productManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productManagement.productManagement.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

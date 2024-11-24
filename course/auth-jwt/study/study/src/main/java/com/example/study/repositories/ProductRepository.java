package com.example.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.domain.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}

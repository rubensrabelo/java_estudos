package com.project.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.product.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package com.productManagement.productManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productManagement.productManagement.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

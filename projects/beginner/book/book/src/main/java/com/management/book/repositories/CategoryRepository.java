package com.management.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.book.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

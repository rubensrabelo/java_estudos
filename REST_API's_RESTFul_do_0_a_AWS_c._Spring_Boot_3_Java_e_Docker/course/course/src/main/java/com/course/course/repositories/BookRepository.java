package com.course.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.course.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

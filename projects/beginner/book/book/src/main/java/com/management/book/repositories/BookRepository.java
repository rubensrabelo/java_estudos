package com.management.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.book.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

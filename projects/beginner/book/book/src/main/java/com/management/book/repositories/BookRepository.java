package com.management.book.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.book.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByTitleContainingIgnoreCase(String title);
	
	List<Book> findByAuthorName(String authorName);
}

package com.management.book.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.book.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	Optional<Author> findAuthorByName(String name);
}

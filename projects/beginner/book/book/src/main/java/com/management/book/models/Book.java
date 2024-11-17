package com.management.book.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_book")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String isbn;
	private LocalDate publicationDate;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	@ManyToMany
	@JoinTable(
				name = "tb_book_category",
				joinColumns = @JoinColumn(name = "book_id"),
				inverseJoinColumns = @JoinColumn(name = "category_id")
			)
	private Set<Category> categories = new HashSet<>();
	
	public Book() {
	}

	public Book(Long id, String title, String isbn, LocalDate publicationDate, Author author) {
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.publicationDate = publicationDate;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	

	public Set<Category> getCategories() {
		return categories;
	}

	public void addCategories(Set<Category> categories) {
		for(Category cat: categories) {
			this.categories.add(cat);
			cat.getBooks().add(this);

		}
	}
	
	public void removeCategory(Category category) {
		this.categories.remove(category);
		category.getBooks().remove(this);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(author, categories, id, isbn, publicationDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(categories, other.categories)
				&& Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(publicationDate, other.publicationDate) && Objects.equals(title, other.title);
	}
}

package com.course.course.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.course.controllers.PersonController;
import com.course.course.data.vo.v1.BookVO;
import com.course.course.exceptions.RequiredObjectsNullException;
import com.course.course.exceptions.ResourceNotFoundException;
import com.course.course.mapper.DozerMapper;
import com.course.course.mapper.custom.PersonMapper;
import com.course.course.models.Book;
import com.course.course.repositories.BookRepository;

@Service
public class BookService {
	
	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private PersonMapper mapper;
	
	public List<BookVO> findAll() {
		logger.info("Finding all Books!");
		
		var books = DozerMapper.parseListObject(repository.findAll(), BookVO.class);
		
		books.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		
		return books;
	}
	
	public BookVO findById(Long id) throws Exception {
		
		logger.info("Finding one BookVO!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public BookVO create(BookVO book) throws Exception {
		
		if(book == null) throw new RequiredObjectsNullException();
		
		logger.info("Creating one book!");
		
		var entity = DozerMapper.parseObject(book, Book.class);
		
		var vo  = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}

	
	public BookVO update(BookVO book) throws Exception {
		if(book == null) throw new RequiredObjectsNullException();
		
		logger.info("Updating one book!");
		
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}
}

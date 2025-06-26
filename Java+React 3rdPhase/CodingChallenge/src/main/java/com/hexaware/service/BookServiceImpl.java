package com.hexaware.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dao.BookRepo;
import com.hexaware.dto.BookDTO;
import com.hexaware.entity.Book;
import com.hexaware.exception.BookNotFoundException;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepo repo;
	
	@Autowired
	ModelMapper modelMap;
	
	public Book dtoToBook(BookDTO bDTO) {
		return modelMap.map(bDTO, Book.class);
	}
	
	public BookDTO bookToDto(Book b) {
		return modelMap.map(b, BookDTO.class);
	}

	@Override
	public BookDTO createBook(BookDTO bDTO) {
		Book b = dtoToBook(bDTO);
		return bookToDto(repo.save(b));
	}

	@Override
	public List<BookDTO> getBook() {
		List<Book> books = repo.findAll();
		return books.stream().map(b -> bookToDto(b)).collect(Collectors.toList());
	}

	@Override
	public BookDTO getBookById(int bookId) {
		return bookToDto(repo.findById(bookId).orElse(null));
	}

	@Override
	public BookDTO updateBook(int bookId, BookDTO bDTO) {
		Book b = repo.findById(bookId).orElse(null);
		if(b != null) {
			b.setTitle(bDTO.getTitle());
			b.setIsbn(bDTO.getIsbn());
			b.setPublicationYear(bDTO.getPublicationYear());
			return bookToDto(repo.save(b));
		} else {
			throw new BookNotFoundException("Book with Id: " + bookId + " not found.");
		}
	}

	@Override
	public Boolean deleteBook(int bookId) {
		Book b = repo.findById(bookId).orElse(null);
		if(b != null) {
			repo.deleteById(bookId);
			return true;
		} else {
			throw new BookNotFoundException("Book with Id: " + bookId + " not found.");
		}
	}

	@Override
	public BookDTO getBookByIsbn(String isbn) {
		return bookToDto(repo.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("Book with ISPN :" + isbn + " not found.")));
	}

	@Override
	public Boolean deleteBookByIsbn(String isbn) {
		BookDTO b = getBookByIsbn(isbn);
		if(b != null) {
			repo.deleteByIsbn(isbn);
			return true;
		} else {
			throw new BookNotFoundException("Book with ISPN: " + isbn + " not found.");
		}
	}
}
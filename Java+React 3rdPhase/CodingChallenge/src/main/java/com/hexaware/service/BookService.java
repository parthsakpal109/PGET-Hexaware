package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.BookDTO;

public interface BookService {
	public BookDTO createBook(BookDTO bDTO);
	public List<BookDTO> getBook();
	public BookDTO getBookById(int bookId);
	public BookDTO updateBook(int bookId, BookDTO bDTO);
	public Boolean deleteBook(int bookId);
	
	public BookDTO getBookByIsbn(String isbn);
	public Boolean deleteBookByIsbn(String isbn);
}
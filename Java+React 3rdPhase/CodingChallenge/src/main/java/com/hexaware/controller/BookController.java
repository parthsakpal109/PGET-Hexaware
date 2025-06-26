package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.BookDTO;
import com.hexaware.service.BookServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
	@Autowired
	BookServiceImpl service;
	
	@PostMapping
	public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bDTO) {
		BookDTO book = service.createBook(bDTO);
		HttpHeaders header = new  HttpHeaders();
		header.add("header Info", "Book Created Successfully");
		return new ResponseEntity<>(book, header, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> getBook() {
		List<BookDTO> books = service.getBook();
		HttpHeaders header = new  HttpHeaders();
		header.add("header Info", "All Books Fetched Successfully");
		return new ResponseEntity<>(books, header, HttpStatus.FOUND);
	}
	
	@GetMapping("/id/{bookId}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable int bookId) {
		BookDTO book = service.getBookById(bookId);
		HttpHeaders header = new  HttpHeaders();
		header.add("header Info", "Book Found Successfully");
		return new ResponseEntity<>(book, header, HttpStatus.FOUND);
	}
	
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
		BookDTO book = service.getBookByIsbn(isbn);
		HttpHeaders header = new  HttpHeaders();
		header.add("header Info", "Book Found Successfully");
		return new ResponseEntity<>(book, header, HttpStatus.FOUND);
	}
	
	@PutMapping("/id/{bookId}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable int bookId, @RequestBody @Valid BookDTO bDTO) {
		BookDTO updatedBook = service.updateBook(bookId, bDTO);
		HttpHeaders header = new  HttpHeaders();
		header.add("header Info", "Book Updated Successfully");
		return new ResponseEntity<>(updatedBook, header, HttpStatus.OK);
	}
	
	@DeleteMapping("/id/{bookId}")
	public ResponseEntity<String> deleteBookById(@PathVariable int bookId) {
		Boolean status = service.deleteBook(bookId);
		HttpHeaders header = new  HttpHeaders();
		if(status == true) {
			header.add("header Info", "Book Deleted Successfully");
			return new ResponseEntity<>("Book Deleted Successfully", header, HttpStatus.OK);
		} else {
			header.add("header Info", "Book Deletion Failed");
			return new ResponseEntity<>("Book Deletion Failed", header, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/isbn/{isbn}")
	public ResponseEntity<String> deleteBookByIsbn(@PathVariable String isbn) {
		Boolean status = service.deleteBookByIsbn(isbn);
		HttpHeaders header = new  HttpHeaders();
		if(status == true) {
			header.add("header Info", "Book Deleted Successfully");
			return new ResponseEntity<>("Book Deleted Successfully", header, HttpStatus.OK);
		} else {
			header.add("header Info", "Book Deletion Failed");
			return new ResponseEntity<>("Book Deletion Failed", header, HttpStatus.NOT_FOUND);
		}
	}
}
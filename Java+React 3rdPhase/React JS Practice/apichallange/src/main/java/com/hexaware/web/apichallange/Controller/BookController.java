package com.hexaware.web.apichallange.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.web.apichallange.DTO.BookDTO;
import com.hexaware.web.apichallange.Entity.Book;
import com.hexaware.web.apichallange.Exception.BookNotFoundException;
import com.hexaware.web.apichallange.Service.BookService;

@CrossOrigin(origins="http://locahost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper mp; 

    
    @PostMapping("/addbook")
    public ResponseEntity<BookDTO> addbook(@RequestBody BookDTO book) {
    	Book book1 = mp.map(book, Book.class);
        Book book2 = bookService.addbook(book1);
        BookDTO book3 = mp.map(book2, BookDTO.class);
        return ResponseEntity.status(201).body(book3);
    }

    
    @GetMapping("/getallbooks")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllbooks();
        List<BookDTO> book=new ArrayList<>();
    	for(Book b : books)
    	{
    		BookDTO x = mp.map(b, BookDTO.class);
    		book.add(x);
    	}
    	if(book.isEmpty()) {
    		return new ResponseEntity<List<BookDTO>>(book,HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<List<BookDTO>>(book,HttpStatus.OK);
    }

    
    @GetMapping("getbookbyisbn/{isbn}")
    public ResponseEntity<?> getBookbyisbn(@PathVariable Long isbn) throws BookNotFoundException {
        Book book = bookService.getbookbyISBN(isbn);
        if (book != null) {
        	BookDTO x = mp.map(book, BookDTO.class);
        	return new ResponseEntity<BookDTO>(x,HttpStatus.OK);
        } else {
        	throw new BookNotFoundException("No Book found with entered ISBN");
        }
    }

    
    @PutMapping("/updatebook/{isbn}")
    public ResponseEntity<BookDTO> updatebook(@PathVariable Long isbn, @RequestBody BookDTO bookDetails) throws BookNotFoundException {
    	Book book1 = mp.map(bookDetails, Book.class);
        Book book2 = bookService.updateBook(isbn, book1);
        if (book2 != null) {
            BookDTO book3 = mp.map(book2, BookDTO.class);
            return new ResponseEntity<BookDTO>(book3,HttpStatus.OK);
        } else {
        	throw new BookNotFoundException("No Book found with entered ISBN");
        }
    }

    
    @DeleteMapping("/deletebook/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable Long isbn) throws BookNotFoundException {
        String str = bookService.deleteBook(isbn);
        if (str.equals("Deleted")) {
            return new ResponseEntity<String>(str,HttpStatus.OK);
        } else {
        	throw new BookNotFoundException("No Book found with entered ISBN");
        }
    }
}

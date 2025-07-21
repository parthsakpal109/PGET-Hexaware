package com.hexaware.web.apichallange.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.web.apichallange.Entity.Book;
import com.hexaware.web.apichallange.Repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
    private BookRepository bookRepository;

	public Book addbook(Book book1) {
		Book book2=bookRepository.save(book1);
		return book2;
	}
	
	public List<Book> getAllbooks() {
		return bookRepository.findAll();
	}
	
	public Book getbookbyISBN(Long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		return book;
	}
	
	public Book updateBook(Long isbn, Book book1) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if(book!=null) {
			book.setAuthor(book1.getAuthor());
			book.setTitle(book1.getTitle());
			book.setPublicationyear(book1.getPublicationyear());
			bookRepository.save(book);
		}
		return book;
	}

	public String deleteBook(Long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if(book!=null) {
	        bookRepository.deleteById(isbn);
	        return "Deleted";
	    }
	    else {
			return "Not Found";
		}
	}
}


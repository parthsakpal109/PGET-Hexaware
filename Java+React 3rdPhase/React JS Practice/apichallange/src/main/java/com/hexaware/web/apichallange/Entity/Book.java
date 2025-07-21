package com.hexaware.web.apichallange.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private long isbn;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String author;
	
	
	private int publicationyear;

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublicationyear() {
		return publicationyear;
	}

	public void setPublicationyear(int publicationyear) {
		this.publicationyear = publicationyear;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publicationyear=" + publicationyear
				+ "]";
	}

	public Book(long isbn, @NotEmpty(message = "Title is mandatory") String title,
			@NotEmpty(message = "Title is mandatory") String author,
			@Min(value = 1850, message = "Publication year should be no earlier than 1750") int publicationyear) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationyear = publicationyear;
	}
	
	public Book() {
		
	}
	
	
}

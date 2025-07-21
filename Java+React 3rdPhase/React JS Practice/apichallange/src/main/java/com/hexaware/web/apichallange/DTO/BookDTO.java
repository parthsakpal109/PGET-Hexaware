package com.hexaware.web.apichallange.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class BookDTO {
	private long isbn;
	private String title;
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
	
	public BookDTO() {
		
	}
	
	public BookDTO(long isbn, String title, String author, int publicationyear) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationyear = publicationyear;
	}
	@Override
	public String toString() {
		return "BookDTO [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publicationyear="
				+ publicationyear + "]";
	}
	
	
}

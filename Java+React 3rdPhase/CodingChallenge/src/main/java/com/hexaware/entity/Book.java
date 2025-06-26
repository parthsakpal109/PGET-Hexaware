package com.hexaware.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@NotNull(message = "Title should'nt be empty.")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50 characters")
	private String title;
	
	@NotNull(message = "ISBN number should'nt be empty.")
	private String isbn;
	
	@NotNull(message = "Publication year should'nt be empty.")
	private int publicationYear;
}
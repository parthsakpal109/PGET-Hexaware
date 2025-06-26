package com.hexaware.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
	public Optional<Book> findByIsbn(String isbn);
	
	@Transactional
	@Modifying
	public long deleteByIsbn(String isbn);
}
package com.hexaware.web.apichallange.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.web.apichallange.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
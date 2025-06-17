package com.example.demo.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	Customer findByActno(int actno);    
    void deleteByActno(int actno);
}

package com.example.demo.JpaRespo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer", nativeQuery = true)
    public List<Customer> getUserN(); 
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customer WHERE actno = :actno", nativeQuery = true)
    int DeleteAc(@Param("actno") int actno);

	public List<Customer> FindAllCustomer();
}
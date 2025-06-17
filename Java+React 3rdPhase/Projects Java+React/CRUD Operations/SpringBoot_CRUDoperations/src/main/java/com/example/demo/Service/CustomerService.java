package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.JPARepo.CustomerRepo;

@Service 
public class CustomerService {

	@Autowired
	CustomerRepo Respo;
	public  Customer dsaveD(Customer c )
	 {
	    Customer  c1=	Respo.save(c);
	    return c1;
		
	 }
	
	public List<Customer> getData1() {
        return Respo.findAll();
    }
	
	public Customer findByActno(int actno) {
        return Respo.findByActno(actno);
    }

    public void deleteByActno(int actno) {
        Respo.deleteByActno(actno);
    }
}
	
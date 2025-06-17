package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Customer;
import com.example.demo.Service.CustomerService;

@RestController 
public class CustomerController {

	@Autowired 
CustomerService  service;
	
	@PostMapping("/saveData")
	public Customer saveData1( @RequestBody Customer c)
	{
		
		Customer c1= service.dsaveD(c);
		return c1;	
	}
	
	@GetMapping("/getUsers")
    public List<Customer> getData() {
        return service.getData1();
    }
	
	@DeleteMapping("/deleteByActno/{actno}")
    public String deleteByActno(@PathVariable int actno) {
        service.deleteByActno(actno);
        return "Deleted Successfully";
    }
}

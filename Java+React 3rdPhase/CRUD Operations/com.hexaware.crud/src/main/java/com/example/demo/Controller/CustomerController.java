package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Customer;
import com.example.demo.Service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/saveData")
    public Customer saveData(@RequestBody Customer c) {
        return service.saveCustomer(c);
    }

    @GetMapping("/getUsers")
    public List<Customer> getData() {
        return service.getAllCustomers();
    }

    @GetMapping("/getById/{actno}")
    public Customer getById(@PathVariable int actno) {
        return service.getById(actno);
    }

    @DeleteMapping("/removeCustomer/{actno}")
    public void removeById(@PathVariable int actno) {
        service.removeById(actno);
    }

    @PutMapping("/updateUser/{actno}/{amt}")
    public void updateById(@PathVariable int actno, @PathVariable double amt) {
        service.updateById(actno, amt);
    }

    @GetMapping("/getUsersJPQL")
    public List<Customer> getDataJPQL() {
        return service.getDataJPQL();
    }

    @GetMapping("/getUsersCustom")
    public List<Customer> getData1() {
        return service.getData1();
    }

    @PutMapping("/withdraw/{actno}/{amt}")
    public String withdrawAmt(@PathVariable int actno, @PathVariable double amt) {
        return service.withdraw(actno, amt);
    }

    @DeleteMapping("/DeleteAct/{ac}")
    public String deleteByAc(@PathVariable int ac) {
        return service.deleteAccountByActNo(ac);
    }
}

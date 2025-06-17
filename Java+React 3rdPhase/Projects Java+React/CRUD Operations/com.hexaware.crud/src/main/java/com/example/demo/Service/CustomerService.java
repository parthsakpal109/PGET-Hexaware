package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.JpaRespo.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer saveCustomer(Customer customer) {
        if (customer.getBalance() < 1000) {
            throw new IllegalArgumentException("Balance must be at least ₹1000.");
        }
        return customerRepo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getById(int actno) {
        return customerRepo.findById(actno).orElse(null);
    }

    public void removeById(int actno) {
        customerRepo.deleteById(actno);
    }

    public void updateById(int actno, double amt) {
        Customer c = getById(actno);
        if (c != null) {
            c.setBalance(c.getBalance() + amt);
            customerRepo.save(c);
        }
    }

    public List<Customer> getDataJPQL() {
        return customerRepo.FindAllCustomer();
    }

    // Another way to get all (could be used for testing or custom sorting later)
    public List<Customer> getData1() {
        return customerRepo.findAll();
    }

    public String withdraw(int actno, double amt) {
        Customer c1 = customerRepo.findById(actno).orElse(null);
        if (c1 != null) {
            double currentBalance = c1.getBalance();
            if (currentBalance - amt >= 1000) {
                c1.setBalance(currentBalance - amt);
                customerRepo.save(c1);
                return "Withdrawal successful.";
            } else {
                throw new IllegalArgumentException("Cannot withdraw. Minimum balance of ₹1000 must be maintained.");
            }
        } else {
            throw new IllegalArgumentException("Account not found.");
        }
    }

    public String deleteAccountByActNo(int actno) {
        int count = customerRepo.DeleteAc(actno);
        if (count > 0) {
            return "Account deleted";
        } else {
            return "No account found for given account number";
        }
    }
}

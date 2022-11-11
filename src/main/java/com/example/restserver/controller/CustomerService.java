package com.example.restserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restserver.domain.Customer;
import com.example.restserver.domain.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {

        double fee = customer.getFee();
        double tax = fee * 0.23;
        double total = fee + tax;
        customer.setTotal(total);
        customer.setTax(tax);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id,Customer customer) {
        if (customerRepository.findById(id).isPresent()) {
            Customer _customer = customerRepository.findById(id).get();
            if (customer.getFirstName() != null) {
                _customer.setFirstName(customer.getFirstName());
            }
            if (customer.getLastName() != null) {
                _customer.setLastName(customer.getLastName());
            }
            if (customer.getEmail() != null) {
                _customer.setEmail(customer.getEmail());
            }
            if (customer.getFee() != 0) {
                _customer.setFee(customer.getFee());
                _customer.setTax(customer.getFee() * 0.23);
                _customer.setTotal(customer.getFee() + _customer.getTax());
            }
            customerRepository.save(_customer);
        }
        return customerRepository.findById(id).get();
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}

package com.example.cjva2.services;

import com.example.cjva2.models.Customer;
import com.example.cjva2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Customer registerCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> login(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent() && bCryptPasswordEncoder.matches(password, customer.get().getPassword())) {
            return customer;
        }
        return Optional.empty();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}

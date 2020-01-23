package com.webcustomer.springdemo.service;

import java.util.List;

import com.webcustomer.springdemo.entity.Customer;

public interface CustomerService {
		
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer c);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
}

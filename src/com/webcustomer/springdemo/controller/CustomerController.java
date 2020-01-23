package com.webcustomer.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcustomer.springdemo.dao.CustomerDAO;
import com.webcustomer.springdemo.entity.Customer;
import com.webcustomer.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model theModel) {
		
		//get customers from dao
		List<Customer> thecustomer = customerService.getCustomers();
		
		//add customers to the model
		theModel.addAttribute("customers",thecustomer);
		
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer thecustomer=new Customer();
		
		theModel.addAttribute("customer", thecustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//get customer from service
		Customer theCustomer=customerService.getCustomer(theId);
		
		//set customer as model attribute to prepopulate the form 
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		//delete customer
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}

}

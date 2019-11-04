package za.co.momentum.activeshoppe.service;

import za.co.momentum.activeshoppe.entity.Customer;

import java.util.List;

public interface CustomerService {

	void addCustomer(Customer customer);
	List<Customer> getAllCustomers();

	Customer findCustomerById(Long id);

	void updateCustomerPoints(Customer customer);

}
package za.co.momentum.activeshoppe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.momentum.activeshoppe.dto.CustomerDTO;
import za.co.momentum.activeshoppe.dto.CustomerPurchaseDTO;
import za.co.momentum.activeshoppe.entity.Customer;
import za.co.momentum.activeshoppe.entity.CustomerPurchase;
import za.co.momentum.activeshoppe.entity.Product;
import za.co.momentum.activeshoppe.service.CustomerService;
import za.co.momentum.activeshoppe.service.ProductService;
import za.co.momentum.activeshoppe.util.ObjectMapperUtils;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/mas/")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping(value="/customer/all")
	@ResponseBody
	public List<CustomerDTO> getAllCustomers(){
		LOGGER.info("Get All the Customers.");
		return ObjectMapperUtils.mapAll(customerService.getAllCustomers(), CustomerDTO.class);
	}

	@GetMapping("/customer/{id}")
	@ResponseBody
	public CustomerDTO findCustomerById(@PathVariable("id") long id){
		LOGGER.info("Find the Customer by the Customer's ID.");
		return convertToDto(customerService.findCustomerById(id));
	}

	@PutMapping("/updatecustomer/")
	@ResponseStatus(HttpStatus.OK)
	public void updateCustomerPoints(@RequestBody final CustomerDTO customerDTO) throws ParseException {
		LOGGER.info("Update the Customer.");
		customerService.updateCustomerPoints(convertToEntity(customerDTO));
	}

	@PostMapping("/customer/add")
	@ResponseStatus(HttpStatus.OK)
	public void createCustomer(@RequestBody final CustomerDTO customerDTO) throws ParseException {
		LOGGER.info("Create a Customer.");
		customerService.addCustomer(convertToEntity(customerDTO));
	}

	private Customer convertToEntity(CustomerDTO customerDTO) throws ParseException {
		return ObjectMapperUtils.map(customerDTO, Customer.class);
	}

	private CustomerDTO convertToDto(Customer customer) {
		return ObjectMapperUtils.map(customer, CustomerDTO.class);
	}

}
